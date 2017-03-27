package org.songdan.tij.classloader.execute;

/**
 * Bytes数组处理工具
 * @author Songdan
 * @date 2016/11/3 10:04
 */
public class ByteUtils {

    public static int bytes2Int(byte[] bytes, int index, int len) {

        int sum = 0;
        int end = index + len;
        for (int i = index; i < end; i++) {
            int n = ((int) bytes[i]) & 0xff;
            n <<= (--len) * 8;
            sum += n;
        }
        return sum;
    }

    public static byte[] int2Bytes(int value,int len) {
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++) {
            b[len - i - 1] = (byte) ((value >> 8 * i) & 0xff);
        }
        return b;
    }

    public static String bytes2String(byte[] b, int start, int len) {
        return new String(b, start, len);
    }

    public static byte[] string2Bytes(String str) {
        return str.getBytes();
    }

    public static byte[] bytesReplace(byte[] originalBytes, int offset, int len, byte[] replaceBytes) {
        byte[] newBytes = new byte[originalBytes.length + (replaceBytes.length - len)];
        System.arraycopy(originalBytes,0,newBytes,0,offset);
        System.arraycopy(replaceBytes,0,newBytes,offset,replaceBytes.length);
        System.arraycopy(originalBytes,offset+len,newBytes,offset+replaceBytes.length,originalBytes.length-offset-len);
        return newBytes;
    }

/*    public static void main(String[] args) {
        byte[] bytes = "hello world".getBytes();
        int sum = bytes2Int(bytes, 0, 5);
        System.out.println(sum);
    }*/
}
