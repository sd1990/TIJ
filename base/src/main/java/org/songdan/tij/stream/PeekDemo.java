package org.songdan.tij.stream;

import java.util.ArrayList;
import java.util.List;

import org.songdan.tij.model.Person;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 07 十二月 2017
 */
public class PeekDemo {

	public static void main(String[] args) {
		create().stream().peek(person -> {
			System.out.println(person);
		}).findFirst();
		System.out.println("================");
		create().stream().peek(person -> {
			System.out.println(person);
		}).filter(person -> {
			return person.getAge()>20;
		}).findFirst();
	}

	static List<Person> create() {
		List<Person> personList = new ArrayList<>();
		for (int i = 0; i < 100; i++) {

			Person person = new Person();
			person.setAge(10+i);
			person.setName("zhangsan" + i);
			personList.add(person);
		}
		return personList;
	}

}
