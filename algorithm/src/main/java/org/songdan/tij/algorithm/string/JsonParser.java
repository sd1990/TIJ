package org.songdan.tij.algorithm.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 没写出来
 *
 * @author song dan
 * @since 28 四月 2018
 */
public class JsonParser {

	public static void main(String[] args) {
		String data = "{\"data\":[{\"id\":1,\"name\":\"nick\"}]}";
		String arr = "[{\"id\": 1, \"name\": \"nick\"}]";
		String bean = "{\"id\": 1, \"name\": \"nick\"}";



	}

	public static Object parse(String str){
		try {
			int i = Integer.parseInt(str);
			return i;
		}
		catch (Exception e) {

		}
		try {
			return Boolean.parseBoolean(str);
		}
		catch (Exception e) {

		}
		if (str.startsWith("[")) {
			parseArr(str);
		}
		else if (str.startsWith("{")) {

			return parseBean(str);
		}
		else {
			return str;
		}
		return null;
	}

	private static Map<String, Object> parseBean(String str) {
		String content = str.substring(str.indexOf("{")+1, str.lastIndexOf("}"));

		String[] entrys = content.split(",");
		Map<String, Object> map = new HashMap<>();
		for (String entry : entrys) {
			String[] arr = entry.split(":");
			String key = arr[0].replace("\"","");
			String value = arr[1].replace("\"","");
			map.put(key, parse(value));
		}
		return map;
	}

	private static void parseArr(String str) {
		String content = str.substring(str.indexOf("{")+1, str.lastIndexOf("}"));
	}


	public static class Person{

		private int id;

		private String name;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

}
