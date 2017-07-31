package org.songdan.tij.algorithm;

import java.util.Base64;

/**
 * 税局便捷二维码内容生成器
 * @author Songdan
 * @date 2017/7/19 19:22
 */
public class QuickQRContentBuilder {

    private static final String BEGIN = "$";

    private static final String VERSION = "01";

    private static final String SEPERATOR = "</>";

    private String taxpayerName;

    private String taxpayerNum;

    private String addressMobile;

    private String bankAccount;

    public QuickQRContentBuilder(String taxpayerName, String taxpayerNum) {
        this(taxpayerName, taxpayerNum, "", "");
    }

    public QuickQRContentBuilder(String addressMobile, String bankAccount, String taxpayerName, String taxpayerNum) {
        this.addressMobile = addressMobile;
        this.bankAccount = bankAccount;
        this.taxpayerName = taxpayerName;
        this.taxpayerNum = taxpayerNum;
    }

    public String build() {
        return BEGIN + Base64.getEncoder().encode(generate()) + BEGIN;
    }

    private byte[] generate() {
        String detail = taxpayerName + SEPERATOR + taxpayerNum + SEPERATOR + addressMobile + SEPERATOR + bankAccount
                + SEPERATOR;
        String crc = new CRC16().calculate(detail);
        return (detail+crc).getBytes();
    }

    public static void main(String[] args) {
        System.out.println(new QuickQRContentBuilder("宋丹","1234567890123456").build());
    }

}
