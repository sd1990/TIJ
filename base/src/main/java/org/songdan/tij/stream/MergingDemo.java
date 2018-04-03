package org.songdan.tij.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.songdan.tij.model.Person;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 29 三月 2018
 */
public class MergingDemo {

	public static void mergeEmpty() {
		List<Person> personList = new ArrayList<>();
		Map<String, Person> map = personList.stream().collect(Collectors.toMap(Person::getName, Function.identity()));
		System.out.println(map);
	}

	public static void main(String[] args) {
		mergeEmpty();
	}

}
