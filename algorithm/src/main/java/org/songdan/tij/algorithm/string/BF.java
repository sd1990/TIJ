package org.songdan.tij.algorithm.string;

import com.google.common.base.Stopwatch;
import com.sun.tools.javac.util.Pair;

import java.util.concurrent.TimeUnit;

/**
 * 暴力匹配算法
 * @author: Songdan
 * @create: 2019-08-17 12:17
 **/
public class BF {

    public static int indexOf(String source,String target) {

        if (source.length()<target.length()) {
            return -1;
        }

        int count = source.length() - target.length() + 1;
        char[] sourceArr = source.toCharArray();
        char[] targetArr = target.toCharArray();
        int length = target.length();
        for (int i = 0; i < count;i++) {
            int j = i;
            for (int k = 0; k < length; k++,j++) {
                if (sourceArr[j]!=targetArr[k]) {
                    break;
                }
            }
            if (j-i==length) {
                return i;
            }
        }
        return -1;
    }

    public static Pair<Integer,Integer> indexOf(char[][] sourArr,char[][] targetArr) {
        if (sourArr.length == 0) {
            return Pair.of(-1, -1);
        }
        if (targetArr.length == 0) {
            return Pair.of(-1, -1);
        }
        if (sourArr.length<targetArr.length || sourArr[0].length<targetArr[0].length) {
            return Pair.of(-1,-1);
        }

        int highCount = sourArr.length - targetArr.length + 1;
        int widthCount = sourArr[0].length - targetArr.length + 1;
        int m = targetArr.length;
        int n = targetArr[0].length;
        a:for (int i = 0; i < highCount; i++) {
            b:for (int j = 0; j < widthCount; j++) {
                int count = 0;
                c:for (int k = 0; k < m; k++) {
                    d:for (int l = 0; l < n; l++) {
                        if(sourArr[i+k][j+l]!=targetArr[k][l]){
                            break c;
                        }
                        count++;
                    }
                }
                if (count == m * n) {
                    return Pair.of(i, j);
                }
            }
        }
        return Pair.of(-1, -1);
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println(indexOf("abcdefghijklmn", "ghi"));
        System.out.println(stopwatch.elapsed(TimeUnit.MICROSECONDS));
        System.out.println("abcdefghijklmn".indexOf("ghi"));
        System.out.println(stopwatch.elapsed(TimeUnit.MICROSECONDS));

        char[][] matrix1 = {
                {'d','a','b','c'},
                {'e','f','a','d'},
                {'c','c','a','f'},
                {'d','e','f','c'}
        };
        char[][] matrix2 = {
                {'c','a'},
                {'e','f'}
        };
        char[][] matrix3 = {
                {'a'},
                {'a'}
        };
        System.out.println(indexOf(matrix1,matrix2));
        System.out.println(stopwatch.elapsed(TimeUnit.MICROSECONDS));
        System.out.println(indexOf(matrix1,matrix3));
        System.out.println(stopwatch.elapsed(TimeUnit.MICROSECONDS));
    }

}
