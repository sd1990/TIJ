package org.songdan.tij.file.util;

import java.io.*;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 文件工具类
 *
 * @author Songdan
 * @date 2016/9/14 9:49
 */
public final class FileUtils {

    /**
     * The number of bytes in a kilobyte.
     */
    public static final long ONE_KB = 1024;

    /**
     * The number of bytes in a megabyte.
     */
    public static final long ONE_MB = ONE_KB * ONE_KB;

    /**
     * The file copy buffer size (30 MB)
     */
    private static final long FILE_COPY_BUFFER_SIZE = ONE_MB * 30;

    /**
     * 复制文件
     *
     * @param sourceFile
     * @param targetFile
     */
    public static void copyFile(File sourceFile, File targetFile) throws IOException {

        if (sourceFile == null || !sourceFile.exists()) {
            throw new FileNotFoundException("源文件为空或找不到");
        }
        if (sourceFile.isDirectory()) {
            throw new IOException("Source '" + sourceFile + "' exists but is a directory");
        }

        if (targetFile == null) {
            throw new NullPointerException("目标文件对象不能为Null");
        }

        File parentFile = targetFile.getParentFile();
        if (parentFile != null) {
            if (!parentFile.mkdirs() && !parentFile.isDirectory()) {
                throw new IOException("Destination '" + parentFile + "' directory cannot be created");
            }
        }
        if (targetFile.exists() && targetFile.canWrite() == false) {
            throw new IOException("Destination '" + targetFile + "' exists but is read-only");
        }

        doCopy(sourceFile, targetFile);

    }

    private static void doCopy(File sourceFile, File targetFile) throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel input = null;
        FileChannel output = null;
        try {
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(targetFile);
            input = fis.getChannel();
            output = fos.getChannel();
            long size = input.size();
            long pos = 0;
            long count = 0;
            while (pos < size) {
                count = size - pos > FILE_COPY_BUFFER_SIZE ? FILE_COPY_BUFFER_SIZE : size - pos;
                pos += output.transferFrom(input, pos, count);
            }
        }
        finally {
            closeQuietly(output);
            closeQuietly(fos);
            closeQuietly(input);
            closeQuietly(fis);
        }

        if (sourceFile.length() != targetFile.length()) {
            throw new IOException("Failed to copy full contents from '" + sourceFile + "' to '" + targetFile + "'");
        }
    }

    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        }
        catch (IOException ioe) {
            // ignore
        }
    }

    public interface FileCompare extends Comparator<File> {

    }

    private static class CustomFileComparator implements FileCompare {

        @Override
        public int compare(File sourceFile, File targetFile) {
            if (!targetFile.exists()) {// 新增
                return 1;
            } else if (getFileMD5(sourceFile).equals(getFileMD5(targetFile))) {
                // 没有变化
                return 0;
            } else {
                // 修改
                return -1;
            }
        }

        private String getFileMD5(File file) {
            MessageDigest md = null;
            InputStream is = null;
            try {
                is = new FileInputStream(file);
                md = MessageDigest.getInstance("MD5");
                md.reset();
                byte[] bytes = new byte[2048];
                int numBytes;
                while ((numBytes = is.read(bytes)) != -1) {
                    md.update(bytes, 0, numBytes);
                }
                byte[] digest = md.digest();
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < digest.length; i++) {
                    sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
                }
                return sb.toString();
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    is.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    private static FileCompare fileCompare = new CustomFileComparator();

    /**
     * 获取sourceDir中与targetDir中有差别的文件
     * 
     * @param sourceDir
     * @param targetDir
     * @throws IOException
     */
    public static void getDifferent(String sourceDir, String targetDir) throws IOException {
        File sourceDirectory = new File(sourceDir);

        File[] files = sourceDirectory.listFiles();

        for (File file : files) {
            String relativePath = getRelativePath(sourceDirectory, file);
            File targetFile = new File(targetDir, relativePath);
            if (file.isFile()) {
                int compare = fileCompare.compare(file, targetFile);
                if (compare == 0) {// 说明是新增或修改
                    file.delete();
                } else {}
            } else {
                getDifferent(file.getPath(), targetFile.getPath());
            }
        }
        if (sourceDirectory.listFiles().length == 0) {
            sourceDirectory.delete();
        }
    }

    /**
     * 比对文件夹,如果有更新或新增操作，覆盖targetDir中的文件
     * 
     * @param sourceDir 源目录
     * @param targetDir 目标目录
     * @return 返回需要更新或新增文件的集合
     */
    public static List<File> compare4Update(String sourceDir, String targetDir) throws IOException {
        List<File> fileList = new ArrayList<>();
        File sourceDirectory = new File(sourceDir);

        File[] files = sourceDirectory.listFiles();

        for (File file : files) {
            String relativePath = getRelativePath(sourceDirectory, file);
            File targetFile = new File(targetDir, relativePath);
            if (file.isFile()) {
                int compare = fileCompare.compare(file, targetFile);
                if (compare != 0) {// 说明是新增或修改
                    //
                    System.out.println(file.getPath());
                    fileList.add(file);
                    // 把有修改的文件复制过去
                    copyFile(file, targetFile);
                } else {}
            } else {
                fileList.addAll(compare4Update(file.getPath(), targetFile.getPath()));
            }
        }
        /*if (sourceDirectory.listFiles().length == 0) {
            sourceDirectory.delete();
            fileList.add(file);
        }*/
        return fileList;
    }

    /**
     * 比较两个文件夹，如果sourceDir有新增的文件，移除
     * 
     * @param sourceDir
     * @param targetDir
     * @return 移除的文件集合
     * @throws IOException
     */
    public static List<File> compare4Remove(String sourceDir, String targetDir) throws IOException {
        List<File> fileList = new ArrayList<>();
        File sourceDirectory = new File(sourceDir);

        File[] files = sourceDirectory.listFiles();

        for (File file : files) {
            String relativePath = getRelativePath(sourceDirectory, file);
            File targetFile = new File(targetDir, relativePath);
            if (file.isFile()) {
                int compare = fileCompare.compare(file, targetFile);
                if (compare == 1) {// 说明该文件是新增的文件
                    System.out.println(file.getPath());
                    fileList.add(file);
                    file.delete();
                }/*else if(compare==1){//说明这个文件是新增的
                    FileUtils.copyFile(file,targetFile);
                 }*/
            } else {
                fileList.addAll(compare4Remove(file.getPath(), targetFile.getPath()));
            }
        }
        if (sourceDirectory.listFiles().length == 0) {
            sourceDirectory.delete();
            fileList.add(sourceDirectory);
        }
        return fileList;
    }

    /**
     * 比对文件夹
     * 
     * @param sourceDir 源目录
     * @param targetDir 目标目录
     */
    public static void compare(String sourceDir, String targetDir) throws IOException {
        // 先移除原来文件中多余的文件
        System.out.println("remove redundant files:");
        compare4Remove(targetDir, sourceDir);
        System.out.println("copy add or modified files:");
        compare4Update(sourceDir, targetDir);
    }

    /**
     * 获取文件与所在最上层的相对路径
     * 
     * @param sourceDirectory
     * @param file
     * @return 相对路径
     */
    private static String getRelativePath(File sourceDirectory, File file) {
        String filePath = file.getAbsolutePath();
        String relativePath = filePath.replace(sourceDirectory.getAbsolutePath() + File.separator, "");
        return relativePath;
    }

    /**
     * 获取文件夹下相同的文件
     * 
     * @param directory 目标文件夹
     * @return
     */
    public static Map<File, List<File>> getTheSame(File directory) {
        List<File> allFiles = getAllFiles(directory);
        System.out.println(allFiles.size());
        Map<File, List<File>> theSameFromList = getTheSameFromList(allFiles, fileCompare);
        return theSameFromList;
    }

    public static <E> Map<E, List<E>> getTheSameFromList(List<E> list, Comparator<E> comparator) {
        Map<E, List<E>> map = new HashMap<>();
        CopyOnWriteArrayList<E> cas = new CopyOnWriteArrayList<>(list);
        Iterator<E> iterator = cas.iterator();
        while (iterator.hasNext()) {
            E e = iterator.next();

            List<E> sameList = new ArrayList<>();
            for (E _e : cas) {

                if (e.equals(_e)) {
                    continue;
                }
                if (comparator.compare(e, _e) == 0) {
                    sameList.add(_e);
                }
            }
            map.put(e, sameList);
            cas.removeAll(sameList);
            cas.remove(e);
            iterator = cas.iterator();
        }


//        for (E e : cas) {
//            List<E> sameList = new ArrayList<>();
//            for (E _e : cas) {
//
//                if (e.equals(_e)) {
//                    continue;
//                }
//                if (comparator.compare(e, _e) == 0) {
//                    sameList.add(_e);
//                }
//            }
//            map.put(e, sameList);
//        }
        return map;
    }

    /**
     * 递归获取文件夹下所有的文件
     * 
     * @param directory
     * @return
     */
    public static List<File> getAllFiles(File directory) {
        List<File> files = new ArrayList<>();
        File[] listFiles = directory.listFiles();
        for (File file : listFiles) {
            if (file.isFile()) {
                files.add(file);
            } else {
                files.addAll(getAllFiles(file));
            }
        }
        return files;
    }

    public static void main(String[] args) throws IOException {
        // compare("C:\\Users\\Songdan\\Desktop\\Aloe0913","C:\\Users\\Songdan\\Desktop\\Aloe0830");
        getDifferent("C:\\Users\\Songdan\\Desktop\\Aloe_new", "C:\\Users\\Songdan\\Desktop\\Aloe_tag");
        // int result = fileCompare.compare(new File("D:\\area.json"), new
        // File("D:\\area2.json"));
        // System.out.println(result);
//        Map<File, List<File>> theSame = getTheSame(new File("F:\\票通\\发票开具平台\\生产数据\\pdfs-09-22"));
//        System.out.println(theSame.size());
//        for (Map.Entry<File, List<File>> fileListEntry : theSame.entrySet()) {
//            System.out.println(fileListEntry.getKey() + ":" + fileListEntry.getValue());
//        }
//        fileCompare.compare(new File("F:\\票通\\发票开具平台\\生产数据\\pdfs-09-22\\778865902434258944.pdf"),new File("F:\\票通\\发票开具平台\\生产数据\\pdfs-09-22\\778865904996978688.pdf"));
    }

}
