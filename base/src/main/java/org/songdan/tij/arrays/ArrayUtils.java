package org.songdan.tij.arrays;

/**
 * @author Songdan
 * @date 2017/3/2 9:35
 */
public class ArrayUtils {

    public static int[] mergeArray(int[] firstArr, int[] secondArr) {
        int[] merge = new int[firstArr.length + secondArr.length];
        System.arraycopy(firstArr,0,merge,0,firstArr.length);
        System.arraycopy(secondArr,0,merge,firstArr.length,secondArr.length);
        return merge;
    }

    public static byte[] mergeArray(byte[] firstArr, byte[] secondArr) {
        byte[] merge = new byte[firstArr.length + secondArr.length];
        System.arraycopy(firstArr,0,merge,0,firstArr.length);
        System.arraycopy(secondArr,0,merge,firstArr.length,secondArr.length);
        return merge;
    }


    public static void main(String[] args) {
/*        int[] array = mergeArray(new int[] { 1, 2, 3 }, new int[] { 1, 2, 3 });
        for (Object o : array) {
            System.out.println(o);
        }*/
        byte[] byteArray = mergeArray(new byte[] { 1, 2, 3 }, new byte[] { 1, 2, 3 });
        for (Object o : byteArray) {
            System.out.println(o);
        }
    }

}
