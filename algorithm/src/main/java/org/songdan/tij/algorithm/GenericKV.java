package org.songdan.tij.algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

/**
 * 数据存取 我们的程序运行过程中用到了一个数组a,数组元素是一个map/dict。 数组元素的“键”和“值”都是字符串类型。在不同的语言中,对应的类型是: PHP的array, Java的HashMap, C++的std::map,
 * Objective-C的 NSDictionary, Swift的Dictionary, Python的dict, JavaScript的object, 等 等 示例: a[0]["key1"]="value1"
 * a[0]["key2"]="value2" a[1]["keyA"]="valueA" ... 为了方便保存和加载,我们使用了一个基于文本的存储结构,数组元素每行 一个:
 * text="key1=value1;key2=value2\nkeyA=valueA\n..." 要求:请实现一个“保存”函数、一个“加载”函数。 text=store(a); //把数组保存到一个文本字符串中
 * a=load(text); //把文本字符串中的内容读取为数组 必须严格按照上述的“每行一个、key=value”的格式保存
 *
 * @author song dan
 * @since 31 一月 2018
 */
public class GenericKV {

    private List<ValueNode> fromArr(Map<String, String>[] mapArray) {
		List<ValueNode> nodeList = Lists.newArrayListWithExpectedSize(mapArray.length * 5);
		for (int i = 0; i < mapArray.length; i++) {
			Map<String, String> map = mapArray[i];
			for (Map.Entry<String, String> entry : map.entrySet()) {
				nodeList.add(new ValueNode(i, entry.getKey(), entry.getValue()));
			}
		}
		return nodeList;
	}

	private void writeFile(List<ValueNode> valueNodes,File file) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for (ValueNode valueNode : valueNodes) {
			writer.write(valueNode.write());
			writer.newLine();
		}
		writer.flush();
	}

    public void store(Map<String,String>[] maps,File file) throws IOException {
		writeFile(fromArr(maps),file);
	}

    public void load(File file) throws IOException {
		List<ValueNode> valueNodes = fromFile(file);
		//TODO 根据index分组
	}

    private List<ValueNode> fromFile(File file) throws IOException {
		ArrayList<ValueNode> nodeList = Lists.newArrayList();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		while ((line = reader.readLine()) != null) {
			nodeList.add(ValueNode.generate(line));
		}
		return nodeList;
	}

	private List<Map<String, String>> toArray(List<ValueNode> valueNodes) {
		return null;
	}

    private static class ValueNode {

        private String key;

        private int index;

        private String value;

        public ValueNode() {
        }

        public ValueNode(int index, String key, String value) {
            this.key = key;
            this.index = index;
            this.value = value;
        }

        public static ValueNode generate(String str) {
            return new ValueNode();
        }

        public String write() {
            return "";
        }
    }

}
