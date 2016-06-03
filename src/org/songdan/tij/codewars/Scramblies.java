package org.songdan.tij.codewars;

import org.junit.Test;

/**
 * 判断第一个字符串的部分能否组装成第二个字符串
 * @author SONGDAN
 *
 */
public class Scramblies {
    public static boolean scramble(String str1,String str2){
        if(str1.indexOf(str2)==-1){
            return false;
        }
        for (int i = 0; i < str2.toCharArray().length; i++) {
            char c=str2.toCharArray()[i];
            int index = str1.indexOf(c);
            if(index==-1){
                return false;
            }else{
                str1=str1.substring(0, index)+str1.substring(index);
            }
        }
        return true;
    }
    public static boolean scramble2(String str1,String str2){
        if(str1.length()==0||str2.length()==0){
            if(str2.length()==0){
                return true;
            }else{
                return false;
            }
        }else{
            char[] chars = str2.toCharArray();
            char c = chars[0];
            int indexOf = str1.indexOf(c);
            if(indexOf==-1){
                return false;
            }else{
                return scramble2(str1.substring(0, indexOf)+str1.substring(indexOf+1), str2.substring(1));
            }
        }
    }
    public static void main(String[] args) {
//        Assert.assertEquals(scramble("javascript", "javascript"), true);
//        Assert.assertEquals(scramble("javscript", "javascript"), false);
//        Assert.assertEquals(scramble("hsjfdwld", "world"), false);
//        Assert.assertEquals(scramble2("javascript", "javascript"), true);
        "a".substring(1);
       /* Assert.assertEquals(scramble2("javscript", "javascript"), false);
        Assert.assertEquals(scramble2("worl", "world"), false);*/
        System.out.println(Scramblies.scramble2("rkqodlw","world"));
        System.out.println(Scramblies.scramble2("cedewaraaossoqqyt","codewars"));
        System.out.println(Scramblies.scramble2("katas","steak"));
        System.out.println(Scramblies.scramble2("scriptjavx","javascript"));
        System.out.println(Scramblies.scramble2("scriptingjava","javascript"));
        System.out.println(Scramblies.scramble2("scriptsjava","javascripts"));
        System.out.println(Scramblies.scramble2("javscripts","javascript"));
        System.out.println(Scramblies.scramble2("aabbcamaomsccdd","commas"));
        System.out.println(Scramblies.scramble2("commas","commas"));
        System.out.println(Scramblies.scramble2("sammoc","commas"));
    }
}

class ScrambliesTest {

    private static void testing(boolean actual, boolean expected) {
        System.out.println(actual==expected);
    }
   
    @Test
    public void test() {
        System.out.println("Fixed Tests scramble");
        testing(Scramblies.scramble("rkqodlw","world"), true);
        testing(Scramblies.scramble("cedewaraaossoqqyt","codewars"),true);
        testing(Scramblies.scramble("katas","steak"),false);
        testing(Scramblies.scramble("scriptjavx","javascript"),false);
        testing(Scramblies.scramble("scriptingjava","javascript"),true);
        testing(Scramblies.scramble("scriptsjava","javascripts"),true);
        testing(Scramblies.scramble("javscripts","javascript"),false);
        testing(Scramblies.scramble("aabbcamaomsccdd","commas"),true);
        testing(Scramblies.scramble("commas","commas"),true);
        testing(Scramblies.scramble("sammoc","commas"),true);
    }
}
