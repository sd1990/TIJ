package org.songdan.tij.file.util;

import org.songdan.tij.generics.Sets;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 24 十一月 2017
 */
public class Fileparser8 {

	public static void main(String[] args) throws IOException, URISyntaxException {
		File file = new File("/Users/songdan/Desktop/error_log_all.tx");
		Set<String> set1 = extractSingleStr(new BufferedReader(new FileReader(file)));
//		String demo = "triggerStateRecord,error wmPoiId=1964787";
//		Set<String> set1 = new HashSet<>();
//		set1.add(demo);
		Pattern pattern = Pattern.compile("wmPoiId(</span>)?=\\d+");
		Set<Long> poiIdSet = new TreeSet<>();
		for (String str : set1) {
			Matcher matcher = pattern.matcher(str);
			while (matcher.find()) {
				String group = matcher.group();
				poiIdSet.add(Long.parseLong(group.split("=")[1]));
			}
		}
		System.out.println(poiIdSet.size());
		StringBuilder build = new StringBuilder("(");
		for (Long wmPoiId : poiIdSet) {
			build.append(wmPoiId);
			build.append(",");
		}
		build.append(")");
		System.out.println(build);
	}

	private static Set<String> extractSingleStr(BufferedReader reader) throws IOException {
		Set<String> set = new LinkedHashSet<>();
		String str;
		while ((str = reader.readLine()) != null) {
			set.add(str);
		}
		return set;
	}

}
