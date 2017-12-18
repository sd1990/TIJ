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
		List<Person> personList1 = create();
		personList1.stream().peek(person -> {
			System.out.println(person);
		}).filter(person -> {
			return person.getAge()>20;
		}).findFirst();
		System.out.println(personList1.size());

		List<Person> personList = create();
		final int[] count = {0};
		personList.parallelStream().forEach(person -> {
			count[0]++;
			System.out.println(person.getName());
		});
		System.out.println(count[0]);
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
