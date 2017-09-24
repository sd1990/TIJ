package org.songdan.tij.reference;

import org.songdan.tij.model.Person;

/**
 * 用来验证java是值传递
 *
 * @author song dan
 * @since 24 九月 2017
 */
public class ReferenceOrValue {

	public static void main(String[] args) {
		Person a = new Person();
		a.setName("songdan");

		Person b = a;

		a = new Person();
		a.setName("shangmingxi");

		System.out.println(a.getName());
		System.out.println(b.getName());

	}

}
