package org.songdan.tij.algorithm.dynamic;

/**
 * 编辑距离
 *
 * @author: Songdan
 * @create: 2019-08-09 11:03
 **/
public class EditDistance {

    /**
     * 从字符串的开始判断
     * @param a
     * @param b
     * @return
     */
    public int distanceRecursive(String a, String b) {
        char[] aCharArr = a.toCharArray();
        char[] bCharArr = b.toCharArray();
        if (aCharArr.length == 0) {
            return bCharArr.length;
        }
        if (bCharArr.length == 0) {
            return aCharArr.length;
        }
        char c1 = aCharArr[0];
        char c2 = bCharArr[0];
        if (c1 == c2) {
            return distanceRecursive(a.substring(1), b.substring(1));
        } else {
            /*
            1. 在a前面添加c2 distanceRecursive = 1+distanceRecursive(a,b.substring(1))
            2. 把a的c1删除，distanceRecursive = 1+distanceRecursive(a.substring(1),b)
            3. 把c1替换为c2，distanceRecursive = 1 + distanceRecursive(a.substring(1),b.substring(1))
             */
            int add = 1 + distanceRecursive(a, b.substring(1));
            int del = 1+ distanceRecursive(a.substring(1),b);
            int replace = 1 + distanceRecursive(a.substring(1), b.substring(1));
            return Math.min(Math.min(add, del), replace);
        }
    }

    /**
     * 从字符串的尾部判断
     * @param a
     * @param b
     * @return
     */
    public int distanceRecursiveTail(String a, String b) {
        char[] aCharArr = a.toCharArray();
        char[] bCharArr = b.toCharArray();
        if (aCharArr.length == 0) {
            return bCharArr.length;
        }
        if (bCharArr.length == 0) {
            return aCharArr.length;
        }
        char c1 = aCharArr[aCharArr.length-1];
        char c2 = bCharArr[bCharArr.length-1];
        if (c1 == c2) {
            return distanceRecursiveTail(a.substring(0,aCharArr.length-1), b.substring(0,bCharArr.length-1));
        } else {
            /*
            1. 在a后面添加c2 distanceRecursive = 1+distanceRecursive(a,b.substring(0,bCharArr.length-1))
            2. 把a的c1删除，distanceRecursive = 1+distanceRecursive(a.substring(0,aCharArr.length-1),b)
            3. 把c1替换为c2，distanceRecursive = 1 + distanceRecursive(a.substring(0,aCharArr.length-1),b.substring(bCharArr.length-1))
             */
            int add = 1 + distanceRecursiveTail(a, b.substring(0,bCharArr.length-1));
            int del = 1+ distanceRecursiveTail(a.substring(0,aCharArr.length-1),b);
            int replace = 1 + distanceRecursiveTail(a.substring(0,aCharArr.length-1), b.substring(0,bCharArr.length-1));
            return Math.min(Math.min(add, del), replace);
        }
    }

    public int distanceDynamic(String a, String b) {
        char[] aCharArr = a.toCharArray();
        char[] bCharArr = b.toCharArray();
        int[][] matrix = new int[bCharArr.length + 1][aCharArr.length + 1];
        //初始化第一行和第一列
        for (int i = 0; i <= aCharArr.length; i++) {
            matrix[0][i] = i;
        }
        for (int i = 0; i <= bCharArr.length; i++) {
            matrix[i][0] = i;
        }
        for (int i = 1; i < matrix.length; i++) {
            int[] row = matrix[i];
            for (int j = 1; j < row.length; j++) {
                if (aCharArr[j - 1] == bCharArr[i - 1]) {
                    matrix[i][j] = matrix[i - 1][j - 1];
                } else {
                    matrix[i][j] = Math.min(matrix[i - 1][j - 1], Math.min(matrix[i][j - 1], matrix[i - 1][j]))+1;
                }
            }
        }
        return matrix[bCharArr.length][aCharArr.length];

    }

    public static void main(String[] args) {
        System.out.println(new EditDistance().distanceRecursive("a", "abc"));
        System.out.println(new EditDistance().distanceRecursiveTail("a", "abc"));
        System.out.println(new EditDistance().distanceDynamic("asdfg", "fab"));
    }

}
