package org.songdan.tij.stream;

import java.util.Optional;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 26 二月 2018
 */
public class OptionalDemo {

	public static void ofNull() {
		System.out.println(Optional.ofNullable(null).map(Object::toString).orElse("null"));
		//在生成of的时候就会NPE
//		System.out.println(Optional.of(null).map(Object::toString).orElse("null"));
	}

	public static void main(String[] args) {
		ofNull();
	}

}
