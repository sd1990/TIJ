package org.songdan.tij.algorithm;

/**
 * 回文
 *
 * @author song dan
 * @since 15 五月 2018
 */
public class Huiwen {

    public static boolean isHuiwen(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            char pre = chars[i];
            char later = chars[chars.length - 1 - i];
            if (pre != later) {
                return false;
            }
        }
        return true;
    }

    public static boolean isHuiwen2(String str) {
        char[] chars = str.toCharArray();
        if (chars.length <= 1) {
            return true;
        }
        if (chars[0] != chars[chars.length - 1]) {
            return false;
        }
        return isHuiwen2(str.substring(1, str.length() - 1));
    }

    public static boolean isHuiwenByNode(Node head) {
		/*
		快慢指针，慢指针前进一步，快指针前进两步
			1. 当快指针到达尾部的时候，慢指针到达中间点
			2. 慢指针在移动的过程中反转节点
			3. 慢指针到达中间点，产生一个新的反向指针，反向遍历，慢指针继续向前，比较内容
		 */
        Node quick = head;
        Node prev = head;
        Node cur = head.next;
        Node next;
        int i = 0;
        if (quick.next != null) {
            quick = quick.next.next;
            i += 2;
        } else {
            quick = null;
            i++;
        }
        while (cur != null && quick != null) {
            //反转节点
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            if (quick.next != null) {
                quick = quick.next.next;
                i++;
                i++;
            } else {
                quick = null;
                i++;
            }
        }
        //很关键
        head.next = null;
        if (cur==null) {
            return true;
        }
        Node reverse = i % 2 == 0 ? prev:prev.next;
        while (reverse != null && cur != null) {
            if (reverse.c != cur.c) {
                return false;
            }
            reverse = reverse.next;
            cur = cur.next;
        }
        return true;
    }



    private static Node quickPoint(Node quick) {
        if (quick.next != null) {
            quick = quick.next.next;
        } else {
            quick = null;
        }
        return quick;
    }

    public static void main(String[] args) {
        System.out.println(isHuiwen2("121"));
        System.out.println(isHuiwen2("1221"));
        System.out.println(isHuiwen("121"));
        System.out.println(isHuiwen("1221"));
        System.out.println(longestPalindrome("ABCCBA"));//babcbabcbaccba
        Node head = new Node('a');
        head.next('b').next('c').next('b').next('a');
        System.out.println(isHuiwenByNode(head));
    }

    /**
     * 提取字符串中最长回文
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s.isEmpty()) {
            return null;
        }
        if (s.length() == 1) {
            return s;
        }
        String longest = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            // get longest palindrome with center of i
            // 考虑奇数情况
            String tmp = helper(s, i, i);
            if (tmp.length() > longest.length()) {
                longest = tmp;
            }

            // get longest palindrome with center of i, i+1
            // 考虑偶数情况
            tmp = helper(s, i, i + 1);
            if (tmp.length() > longest.length()) {
                longest = tmp;
            }
        }
        return longest;
    }

    // Given a center, either one letter or two letter,
// Find longest palindrome
    public static String helper(String s, int begin, int end) {
        while (begin >= 0 && end <= s.length() - 1
                && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }
        return s.substring(begin + 1, end);
    }

    static class Node {
        Node next;
        char c;

        public Node(char c) {
            this.c = c;
        }

        public Node next(char c) {
            next = new Node(c);
            return next;
        }
    }

}
