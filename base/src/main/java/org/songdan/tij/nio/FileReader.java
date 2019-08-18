package org.songdan.tij.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: Songdan
 * @create: 2019-08-17 16:25
 **/
public class FileReader {

    private String fileName;

    private FileChannel fileChannel;

    private RandomAccessFile randomAccessFile;

    public FileReader(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.randomAccessFile = new RandomAccessFile(fileName, "rw");
    }

    public void read() throws IOException {
        FileChannel channel = randomAccessFile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int pos = channel.read(byteBuffer);
        while (pos != -1) {
            System.out.println("poi is" + pos);
            //反转buffer,将buffer的position改为0，limit改为position
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                System.out.print((char) byteBuffer.get());
            }
            //重新读取buffer
            byteBuffer.rewind();
            while (byteBuffer.hasRemaining()) {
                System.out.print((char) byteBuffer.get());
            }
            //buffer的position为0，limit为capacity
            byteBuffer.clear();
            pos = channel.read(byteBuffer);
        }
        channel.close();
    }

    public static void main(String[] args) throws IOException {
        new FileReader("TIJ/base/123.txt").read();
    }
}
