package org.songdan.tij.string;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 12 十二月 2017
 */
public class RegexpDemo {

	public void lineSeperator(String str) {
		Pattern compile = Pattern.compile("\\r\\n|\\n|\\\\n|\\\\r\\\\n");
		Matcher matcher = compile.matcher(str);
		while (matcher.find()) {
			System.out.println(matcher.group());
		}
	}

	public static String extractWildCardStr(String targe, Pattern pattern) {
		Matcher matcher = pattern.matcher(targe);
		System.out.println(matcher.groupCount());
		int i = 0;
		while (matcher.find()) {
			System.out.println(matcher.group(i++));
		}
		return "";
	}


	public static List<String> extractWildCardStr(String targe, String regex) {
		String[] split = regex.split("\\*");
		for (String replace : split) {
			targe = targe.replace(replace, "|");
		}
		return Arrays.stream(targe.split("\\|")).filter(s-> !"".equals(s)).collect(Collectors.toList());
	}

	public static List<String> extractWildCardStrV2(String targe, String regex) {
		String[] split = regex.split("\\*");
		List<String> collect = Arrays.stream(split).filter(s -> !"".equals(s)).collect(Collectors.toList());
		String replace = String.join("|", collect);
		return Arrays.asList(targe.replaceAll(replace, " ").trim().split(" "));
	}

	public static void test() {
		Pattern regexp = Pattern.compile("[^0-9a-zA-Z_\\-.:]+");
		System.out.println(regexp.matcher("192.168.1.1:8892").matches());
		System.out.println(regexp.matcher("www.baidu.com@www.sina.com").find());
		System.out.println("www.baidu.com@www.sina.com".split("[^0-9a-zA-Z_\\-.:]+")[0]);
	}

	public static void main(String[] args) {
//		RegexpDemo regexpDemo = new RegexpDemo();
//		regexpDemo.lineSeperator("123\r\n456");
//		String seperator = System.lineSeparator();

//		String[] split = "123\r\n456".split("\\r\\n|\\n");
//		String target = "12\\n12\\n12\\n222\\nfsdfsd\\n";
//		regexpDemo.lineSeperator(target);
//		System.out.println(target);
//		String[] split = target.split("\\r\\n|\\n|\\\\n|\\\\r\\\\n");
//		for (String s : split) {
//			System.out.println(s);
//		}
		Pattern main = Pattern.compile("main");
		boolean b = main.matcher("main.status").find();
		System.out.println(b);
		String watchPath = "biz.*.status";
		Matcher matcher = Pattern.compile("subTaskList\\.(\\d+)\\.status").matcher("subTaskList.12.status");
		if(matcher.matches()) {
			System.out.println(matcher.group(1));
		}
		Pattern bizStatus = Pattern.compile(watchPath.substring(0,watchPath.lastIndexOf(".")));
//		System.out.println(bizStatus.matcher("biz.1.status").find());
//		System.out.println(extractWildCardStr("biz.1.status",Pattern.compile("(biz(.*)status)")));
//		System.out.println(extractWildCardStr("biz.1.status.2","biz.*.status.*"));
//		System.out.println(extractWildCardStrV2("biz.1.status.2","biz.*.status.*"));
		test();
		System.out.println("[1,2,3]".replaceAll("[\\[\\]]", ""));
	}

}
