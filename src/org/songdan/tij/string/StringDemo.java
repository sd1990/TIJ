package org.songdan.tij.string;

public class StringDemo {

    public static void main(String[] args) {
        String str1="abcdef";
        String str2="abcdef";
        String str3=new String(str1);
        String str4=new String(str1);
        System.out.println(str1==str2);
        System.out.println(str3==str4);
        //---------------------------
        System.out.println(unescapeBackslash("\\\\\\n\\t"));
        System.out.println(StringUtils.unescape_perl_string("\\\\\\n\\t"));
        
    }
    
    public static String unescapeBackslash(String oldStr){
        //上个字符是否是反斜杠
        boolean saw_backslash = false;
        StringBuilder sb = new StringBuilder(oldStr.length());
        //循环遍历
        char[] charArray = oldStr.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            //
            if(c=='\\'){
                if(!saw_backslash){
                    saw_backslash=true;
                }else{
                    sb.append(c);
                    saw_backslash=false;
                }
            }else{
                switch (c) {
                    case 't':
                        sb.append("\\t");
                        break;
                    case 'n':
                        sb.append("\\n");
                        break;

                    default:
                        sb.append(c);
                        break;
                }
                saw_backslash=false;
            }
                    
        }
        return sb.toString();
    }
}
