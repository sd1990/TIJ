package org.songdan.tij.file.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 05 十二月 2017
 */
public class StringComposite {
	public static void main(String[] args) {

		String shop = "100001730\n" +
				"100056767\n" +
				"100006755\n" +
				"100033775\n" +
				"100651707\n" +
				"100026723\n" +
				"100021732\n" +
				"100078712\n" +
				"100073789\n" +
				"100016712\n" +
				"100029722\n" +
				"100056712\n" +
				"100078741\n" +
				"100022773\n" +
				"100061739\n" +
				"100023720\n" +
				"100056780\n" +
				"100021753\n" +
				"100056784\n" +
				"100029764\n" +
				"100075729\n" +
				"100029767\n" +
				"100020721\n" +
				"100017702\n" +
				"100075708\n" +
				"100786756\n" +
				"100021701\n" +
				"100062742\n" +
				"100073740\n" +
				"100073706\n" +
				"100011709\n" +
				"100075718\n" +
				"100698770\n" +
				"100073719\n" +
				"100022795\n" +
				"100026784\n" +
				"100025714\n" +
				"100020702\n" +
				"100016701\n" +
				"100075709\n" +
				"100025709\n" +
				"100056771\n" +
				"100075703\n" +
				"100078738";
		String sku = "76010003\t2\n" +
				"76010004\t2\n" +
				"76010005\t2\n" +
				"76010006\t3\n" +
				"76010007\t3\n" +
				"76020009\t2\n" +
				"76020008\t4\n" +
				"76010008\t4\n" +
				"76010009\t4\n" +
				"76010010\t4\n" +
				"76050003\t3\n" +
				"76050004\t3\n" +
				"76040001\t3\n" +
				"76040002\t2\n" +
				"76040003\t2\n" +
				"76030001\t3\n" +
				"76030002\t3\n" +
				"76050001\t3\n" +
				"76050002\t3\n" +
				"76020007\t2";

		String[] skuStr = sku.split("\\n");
		String[] shops = shop.split("\\n");
		String format = "update shelf_goods_relation set amount = %s where shelf_code = '%s' and sku_code = '%s' and equipment_type = 1;";
		StringBuilder builder = new StringBuilder();
		for (String shopstr : shops) {

			for (String str : skuStr) {
				String[] split = str.split("\\t");
				String sql = String.format(format, split[1], shopstr, split[0]);
				builder.append(sql);
				builder.append(System.lineSeparator());


			}
		}

		System.out.println(builder.toString());

	}
}
