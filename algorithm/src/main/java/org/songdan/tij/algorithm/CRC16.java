package org.songdan.tij.algorithm;

/**
 * CRC-16算法
 * @author Songdan
 * @date 2017/7/19 18:13
 */
public class CRC16 {

    public static void main(String... a) {
        byte[] bytes = new byte[] { (byte) 0x08, (byte) 0x68, (byte) 0x14, (byte) 0x93, (byte) 0x01, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06, (byte) 0x00, (byte) 0x00, (byte) 0x01,
                (byte) 0x00, (byte) 0x13, (byte) 0x50, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x22, (byte) 0x09,
                (byte) 0x11 };
        byte[] byteStr = new byte[4];
//        Integer crcRes = new CRC16().calculateCrc("宋丹</>1234567890123456</></></>".getBytes());
        Integer crcRes = new CRC16().calculateCrc(bytes);
        System.out.println(Integer.toHexString(crcRes));

        byteStr[0] = (byte) ((crcRes & 0x000000ff));
        byteStr[1] = (byte) ((crcRes & 0x0000ff00) >>> 8);

        System.out.printf("%02X\n%02X", byteStr[0], byteStr[1]);
        System.out.println();
        String format = String.format("%02X", byteStr[1]);
        System.out.println(format);
    }

    /**
     * 返回数组第一个元素为高八位，第二个元素为低八位
     * @param target
     * @return CRC校验值
     */
    public String calculate(String target) {
        byte[] byteStr = new byte[4];
        int calculateCrc = calculateCrc(target.getBytes());
        byteStr[0] = (byte) ((calculateCrc & 0x0000ff00) >>> 8);
        byteStr[1] = (byte) ((calculateCrc & 0x000000ff));
        return String.format("%02X%02X", byteStr[0], byteStr[1]);
    }

    private int calculateCrc(byte[] bytes) {
        int i;
        int crcValue = 0;
        for (int len = 0; len < bytes.length; len++) {
            for (i = 0x80; i != 0; i >>= 1) {
                if ((crcValue & 0x8000) != 0) {
                    crcValue = (crcValue << 1) ^ 0x8005;
                } else {
                    crcValue = crcValue << 1;
                }
                if ((bytes[len] & i) != 0) {
                    crcValue ^= 0x8005;
                }
            }
        }
        return crcValue;
    }

}
