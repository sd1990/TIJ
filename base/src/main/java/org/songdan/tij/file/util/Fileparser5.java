package org.songdan.tij.file.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 24 十一月 2017
 */
public class Fileparser5 {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("/Users/songdan/shelf_sku.txt");
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
				String[] strs = str.trim().split("\\s");
				String id = strs[0];
				if ("".equals(id)) {
					continue;
				}
				builder.append(String.format("insert into shelf_goods_relation (shelf_code,sku_code, sku_type, status, status_wish,amount) values('%s','%s','日配','Y',1,%s) on DUPLICATE KEY update status='Y',status_wish=1,amount=%s;",strs[0],strs[1],strs[2],strs[2])).append(System.lineSeparator());
			}
			System.out.println(line);
			System.out.println(builder.toString());
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
