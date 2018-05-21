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
			char later = chars[chars.length-1-i];
			if (pre != later) {
				return false;
			}
		}
		return true;
	}

	public static boolean isHuiwen2(String str) {
		char[] chars = str.toCharArray();
		if (chars.length<= 1) {
			return true;
		}
		if (chars[0] != chars[chars.length - 1]) {
			return false;
		}
		return isHuiwen2(str.substring(1, str.length() - 1));
	}

	public static void main(String[] args) {
		System.out.println(isHuiwen2("121"));
		System.out.println(isHuiwen2("1221"));
		System.out.println(isHuiwen("121"));
		System.out.println(isHuiwen("1221"));
		System.out.println(longestPalindrome("ABCCBA"));//babcbabcbaccba
	}

	public  static String longestPalindrome(String s) {
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
		String subS = s.substring(begin + 1, end);
		return subS;
	}

}
