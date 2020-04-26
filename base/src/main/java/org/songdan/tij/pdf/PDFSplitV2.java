/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.songdan.tij.pdf;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the main program that will take a pdf document and split it into
 * a number of other documents.
 *
 * @author Ben Litchfield
 */
public final class PDFSplitV2 {
    @SuppressWarnings({"squid:S2068"})
    private static final String PASSWORD = "-password";
    private static final String SPLIT = "-split";
    private static final String START_PAGE = "-startPage";
    private static final String END_PAGE = "-endPage";
    private static final String OUTPUT_PREFIX = "-outputPrefix";
    private static final String OUT_PATH = "-outPath";

    private PDFSplitV2() {
    }

    /**
     * Infamous main method.
     *
     * @param args Command line arguments, should be one and a reference to a file.
     * @throws IOException If there is an error parsing the document.
     */
    public static void main(String[] args) throws IOException {
        // suppress the Dock icon on OS X
        System.setProperty("apple.awt.UIElement", "true");

        PDFSplitV2 split = new PDFSplitV2();
        split.split(args);
    }

    private void split(String[] args) throws IOException {
        @SuppressWarnings({"squid:S2068"})
        String password = "";
        String split = null;
        Splitter splitter = new Splitter();
        String pdfFile = null;
        String outputPrefix = null;
        String outPath = null;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case PASSWORD:
                    i++;
                    if (i >= args.length) {
                        usage();
                    }
                    password = args[i];
                    break;
                case SPLIT:
                    i++;
                    if (i >= args.length) {
                        usage();
                    }
                    split = args[i];
                    break;
                case OUTPUT_PREFIX:
                    i++;
                    outputPrefix = args[i];
                    break;
                case OUT_PATH:
                    i++;
                    outPath = args[i];
                    break;
                default:
                    if (pdfFile == null) {
                        pdfFile = args[i];
                    }
                    break;
            }
        }

        if (pdfFile == null || split == null) {
            usage();
        } else {
            if (outputPrefix == null) {
                outputPrefix = pdfFile.substring(0, pdfFile.lastIndexOf('.'));
            }
            PDDocument document = null;
            try {
                document = PDDocument.load(new File(pdfFile), password);

                Integer[] splitArr = Arrays.stream(split.split(",")).map(Integer::parseInt).toArray(Integer[]::new);
                if (splitArr.length == 1) {
                    split0(splitter, outputPrefix, outPath, document, splitArr[0], null);
                } else {
                    for (int i = 0; i < splitArr.length - 1; i++) {
                        Integer startPage = splitArr[i];
                        Integer endPage = splitArr[i + 1];
                        split0(splitter, outputPrefix, outPath, document, startPage, endPage);
                    }
                }

            } finally {
                if (document != null) {
                    document.close();
                }

            }
        }
    }

    private void split0(Splitter splitter, String outputPrefix, String outPath, PDDocument document, Integer startPage, Integer endPage) throws IOException {
        List<PDDocument> documents = null;
        try {
            boolean endPageSet = false;
            if (startPage != null) {
                splitter.setStartPage(startPage);
            }
            if (endPage != null) {
                splitter.setEndPage(endPage);
                endPageSet = true;
            }
            int numberOfPages = document.getNumberOfPages();
            if (endPageSet) {
                splitter.setSplitAtPage(endPage - startPage);
            } else {
                splitter.setSplitAtPage(numberOfPages);
            }
            File parent = new File(".");
            if (outPath != null) {
                parent = new File(outPath);
                if (!parent.exists()) {
                    parent.mkdirs();
                }
            }
            documents = splitter.split(document);
            int currentPage = startPage;
            for (int j = 0; j < documents.size(); j++) {
                try (PDDocument doc = documents.get(j)) {
                    int pageNumbers = doc.getNumberOfPages();
                    doc.save(new File(parent, outputPrefix + "-" + currentPage + "-" + ((currentPage += pageNumbers) - 1) + ".pdf"));
                }
            }
        }finally {
            for (int n = 0; documents != null && n < documents.size(); n++) {
                PDDocument doc = documents.get(n);
                doc.close();
            }
        }
    }

    /**
     * This will print the usage requirements and exit.
     */
    private static void usage() {
        String message = "Usage: java -jar pdfbox-app-x.y.z.jar PDFSplit [options] <inputfile>\n"
                + "\nOptions:\n"
                + "  -password  <password>  : Password to decrypt document\n"
                + "  -split     <integer>   : split after this many pages (default 1, if startPage and endPage are unset)\n"
                + "  -startPage <integer>   : start page\n"
                + "  -endPage   <integer>   : end page\n"
                + "  -outputPrefix <prefix> : Filename prefix for splitted files\n"
                + "  <inputfile>            : The PDF document to use\n";

        System.err.println(message);
        System.exit(1);
    }
}