package org.songdan.tij.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 12 十二月 2017
 */
public class RegexpDemo {

	public void lineSeperator(String str) {
		Pattern compile = Pattern.compile("\\r\\n|\\n");
		Matcher matcher = compile.matcher(str);
		while (matcher.find()) {
			System.out.println(matcher.group());
		}
	}

	public static void main(String[] args) {
		RegexpDemo regexpDemo = new RegexpDemo();
//		regexpDemo.lineSeperator("123\r\n456");
//		String seperator = System.lineSeparator();

//		String[] split = "123\r\n456".split("\\r\\n|\\n");
		String target = "12\\n12\\n12\\n222\\nfsdfsd\\n";
//		System.out.println(target);
		String[] split = target.split("\\r\\n|\\n|\\\\n|\\\\r\\\\n");
		for (String s : split) {
			System.out.println(s);
		}
	}

}
