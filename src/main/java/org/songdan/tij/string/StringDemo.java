package org.songdan.tij.string;

public class StringDemo {

    public static String unescapeBackslash(String oldStr) {
        //上个字符是否是反斜杠
        boolean saw_backslash = false;
        StringBuilder sb = new StringBuilder(oldStr.length());
        //循环遍历
        char[] charArray = oldStr.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            //
            if (c == '\\') {
                if (!saw_backslash) {
                    saw_backslash = true;
                }
                else {
                    sb.append(c);
                    saw_backslash = false;
                }
            }
            else {
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
                saw_backslash = false;
            }

        }
        return sb.toString();
    }
}
