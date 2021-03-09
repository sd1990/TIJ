package org.songdan.tij.pdf;

import com.google.common.collect.Lists;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.songdan.tij.string.StringUtils;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: Songdan
 * @create: 2021-02-05 15:28
 **/
public class PDFUtil {

    /**
     * 将目录下所有的pdf合并为一个pdf
     * @param parent
     */
    public static void mergePdf(File parent,String filterStr,String reverseStr,String resultName) throws IOException {
        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
        File[] files = parent.listFiles();
        List<InputStream> sources = Stream.of(files).filter(file -> {
            return file.getName().endsWith(".pdf");
        }).filter(file->{
            if (filterStr != null) {
                return file.getName().contains(filterStr);
            }
            return true;
        }).filter(file->{
            if (reverseStr != null) {
                return !file.getName().contains(reverseStr);
            }
            return true;
        }).map(file -> {
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        pdfMergerUtility.addSources(sources);
        pdfMergerUtility.setDestinationFileName(resultName);
        pdfMergerUtility.setAcroFormMergeMode(PDFMergerUtility.AcroFormMergeMode.JOIN_FORM_FIELDS_MODE);
        pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
    }

    public static void main(String[] args) throws IOException {
        mergePdf(new File("/Users/songdan/Desktop/暖暖作业/11-21"),null,"答案","/Users/songdan/Desktop/暖暖作业/11-21(无答案).pdf");
        mergePdf(new File("/Users/songdan/Desktop/暖暖作业/11-21"),"答案",null,"/Users/songdan/Desktop/暖暖作业/11-21(答案).pdf");
    }



}
