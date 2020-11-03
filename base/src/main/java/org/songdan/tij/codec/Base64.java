package org.songdan.tij.codec;

/**
 * @author: Songdan
 * @create: 2020-07-13 15:25
 **/
public class Base64 {

    public static void main(String[] args) {
        String result = new String(java.util.Base64.getDecoder().decode("W3sieCI6NDAxMDEyMjcsInkiOjExNjAyOTE1MH0seyJ4Ijo0MDEwMDUzMSwieSI6MTE2MDMzNzYzfSx7IngiOjQwMDk4NTMwLCJ5IjoxMTYwMzc2ODN9LHsieCI6NDAwOTU1MzAsInkiOjExNjA0MDMyMH0seyJ4Ijo0MDA5MTk3NiwieSI6MTE2MDQxMjM5fSx7IngiOjQwMDg4NDIzLCJ5IjoxMTYwNDAzMTh9LHsieCI6NDAwODU0MjQsInkiOjExNjAzNzY4MH0seyJ4Ijo0MDA4MzQyNCwieSI6MTE2MDMzNzYyfSx7IngiOjQwMDgyNzI4LCJ5IjoxMTYwMjkxNTB9LHsieCI6NDAwODM0MjQsInkiOjExNjAyNDUzN30seyJ4Ijo0MDA4NTQyNCwieSI6MTE2MDIwNjE5fSx7IngiOjQwMDg4NDIzLCJ5IjoxMTYwMTc5ODF9LHsieCI6NDAwOTE5NzYsInkiOjExNjAxNzA2MH0seyJ4Ijo0MDA5NTUzMCwieSI6MTE2MDE3OTc5fSx7IngiOjQwMDk4NTMwLCJ5IjoxMTYwMjA2MTZ9LHsieCI6NDAxMDA1MzEsInkiOjExNjAyNDUzNn1d"));
        System.out.println(result);
    }

}
