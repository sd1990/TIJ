package org.songdan.tij.enums;

/**
 * Enum工具类
 *
 * @author song dan
 * @since 21 九月 2017
 */
public final class EnumUtils {

	private EnumUtils() {

	}

	public static <T extends Enum<T>> T getByCode(Class<T> enumClass, String code) {
		if (CodeRetreievable.class.isAssignableFrom(enumClass)) {
			if (enumClass.isEnum()) {
				T[] enumConstants = enumClass.getEnumConstants();
				CodeRetreievable[] codeRetreievables = (CodeRetreievable[]) enumConstants;
				for (CodeRetreievable codeRetreievable : codeRetreievables) {
					if (code.equals(codeRetreievable.getCode())) {
						return (T) codeRetreievable;
					}
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		Demo demo = getByCode(Demo.class, "2");
		System.out.println(demo);
	}


}

enum Demo implements CodeRetreievable{

	A("1"),B("2");

	private String num;

	Demo(String num) {
		this.num = num;
	}

	public String getNum() {
		return num;
	}

	@Override
	public String getCode() {
		return getNum();
	}
}

interface CodeRetreievable {

	String getCode();

}