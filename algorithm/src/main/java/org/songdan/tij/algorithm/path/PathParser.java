package org.songdan.tij.algorithm.path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

/**
 * JSON格式转换 在某个特定应用场景中,我们有一个从JSON获取的内容,比如:
 m = { "a": 1, "b": { "c": 2, "d": [3,4] } } 现在需要把这个层级的结构做展开,只保留一层key/value结构。对于上述 输入,需要得到的结构是:
 o = {"a": 1, "b.c": 2, "b.d": [3,4] }
 也就是说,原来需要通过 m["b"]["c"] 访问的值,在展开后可以通过 o["b.c"] 访问。
 请实现这个“层级结构展开”的代码。
 输入:任意JSON(或者map/dict) 输出:展开后的JSON(或者map/dict)
 *
 * @author song dan
 * @since 31 一月 2018
 */
public class PathParser {

	public static class ValueNode{

		static ValueNode ROOT = new ValueNode(null, "$", null);

		private ValueNode parent;

		private String key;

		private Object value;

		public ValueNode(ValueNode parent, String key, Object value) {
			this.parent = parent;
			this.key = key;
			this.value = value;
		}

		public String path() {
			if (parent != null) {
				return parent.path() + "." + key;
			}else {
				return key;
			}
		}

	}

	public List<ValueNode> parse(ValueNode parent,Map<String, Object> map) {
		List<ValueNode> list = new ArrayList<>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			ValueNode valueNode = new ValueNode(parent, entry.getKey(), entry.getValue());
			Object value = entry.getValue();
			if (value instanceof Map) {
				list.addAll(parse(valueNode,(Map<String, Object>) entry.getValue()));
			}else{
				list.add(valueNode);
			}
		}
		return list;
	}

	public static void main(String[] args) {
		Map<String, Object> root = new HashMap<>();
		root.put("a", "1");
		Map<String, Object> b = new HashMap<>();
		root.put("b", b);

		b.put("c", 2);
		b.put("d", Lists.newArrayList(3, 4));

		List<ValueNode> parse = new PathParser().parse(ValueNode.ROOT, root);
		parse.forEach(valueNode -> {
			System.out.println(String.format("%s=%s", valueNode.path(), valueNode.value));
		});

	}

}
