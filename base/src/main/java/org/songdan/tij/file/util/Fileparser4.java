package org.songdan.tij.file.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.songdan.tij.string.StringUtils;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 24 十一月 2017
 */
public class Fileparser4 {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("/Users/songdan/all_sku.csv");
		BufferedReader reader = null;
		try {
			System.out.println("读一整行:");
			// 读取非汉字可用
			// reader = new BufferedReader(new FileReader(file));
			// 读汉字可用
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String str;
			int line = 0;
			StringBuilder builder = new StringBuilder();
			while ((str = reader.readLine()) != null) {
				line++;
				System.out.println(str);
				String[] strs = str.trim().split(",");
				String id = strs[0];
				if ("".equals(id)) {
					continue;
				}
				builder.append(String.format("update shelf_goods_relation set sku_type = '%s' where sku_code = '%s' and sku_type = '未知';",strs[2],strs[0])).append(System.lineSeparator());
			}
			System.out.println(line);
			System.out.println(builder.substring(0,builder.length()-1));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
