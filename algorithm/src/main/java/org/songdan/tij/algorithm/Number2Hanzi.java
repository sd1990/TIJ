package org.songdan.tij.algorithm;

/**
 * 阿拉伯数字转汉字
 *
 * @author song dan
 * @since 15 五月 2018
 */
public class Number2Hanzi {

    private static String[] hanziArr = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };

    private static String[] unitArr = { "", "十", "百", "千","万","十", "百", "千","亿","十", "百", "千","万"};

    private static String toHanzi(int num) {
        StringBuilder builder = new StringBuilder();
        while (num > 0) {
            int i = num % 10;
            builder.append(hanziArr[i]);
            num = num / 10;
        }
        return builder.reverse().toString();
    }

    private static String format2(int num) {
		boolean zero = false;
		boolean start = false;
		char[] chars = String.valueOf(num).toCharArray();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			int val = Integer.valueOf(chars[i]+"");
			if (val != 0 &&!start) {
				start = true;
			}
			//跳过开头的0
			if (!start) {
				continue;
			}
			int index = chars.length - i - 1;
			// 处理单位
			// 处理0
			if (val ==0) {
				if (!zero) {
					zero = true;
				}
				if (index == 4 || index == 8) {
					//判断对应的万 或者亿 是否为0
					int sum = 0;
					for (int pre = i - 1;pre>=i-3 && pre>=0;pre--) {
						sum += Integer.valueOf(chars[pre]+"");
					}
					if (sum > 0) {
						System.out.println(sum);
						builder.append(unitArr[index]);
					}
				}
			} else {
				if (zero) {
					// 遇到非零的才添加零
					builder.append(hanziArr[0]);
				}
				builder.append(hanziArr[val]);
				builder.append(unitArr[index]);
				zero = false;
			}
		}
		return builder.toString();
	}

    public static void main(String[] args) {
//        System.out.println(format(245000006));
//        System.out.println(format(200000006));
//        System.out.println(format(0245000006));
//		System.out.println(format(1)+"<>"+format2(1));
//		System.out.println(format(10)+"<>"+format2(10));
//		System.out.println(format(101)+"<>"+format2(101));
		System.out.println(format2(100000001));
		System.out.println(format2(101000001));
		System.out.println(format2(100010001));
		System.out.println(format2(100010001));
	}

}
