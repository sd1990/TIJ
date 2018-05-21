package org.songdan.tij.algorithm;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Joiner;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 19 五月 2018
 */
public interface Morable<T>{
	Collection<T> more();

	class Person implements Morable<Person>{

		private Person up;

		String name;

		String phone;

		public Person(String name, String phone) {
			this.name = name;
			this.phone = phone;
		}

		Set<Person> friends = new HashSet<>();

		public Person search(Person p) {
			Person result = new WFS<>(this).search(p, (o1, o2) -> {
				int nameResult = o1.name.compareTo(o2.name);
				return nameResult == 0 ? o1.phone.compareTo(o2.phone) : nameResult;
			});
			return result;
		}

		public Person searchWithStep(Person p) {
			Person result = new WFS<>(this).searchWithLevel(p, (o1, o2) -> {
				int nameResult = o1.name.compareTo(o2.name);
				return nameResult == 0 ? o1.phone.compareTo(o2.phone) : nameResult;
			});
			return result;
		}

		@Override
		public Collection<Person> more() {
			return Collections.unmodifiableSet(friends);
		}

		public boolean add(Person person) {
			person.up = this;
			return friends.add(person);
		}

		public String path() {
			if (up == null) {
				return name;
			}
			return Joiner.on("<-").join(name, up.path());
		}

		public String getName() {
			return name;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Person person = (Person) o;

			if (name != null ? !name.equals(person.name) : person.name != null) return false;
			return phone != null ? phone.equals(person.phone) : person.phone == null;
		}

		@Override
		public int hashCode() {
			int result = name != null ? name.hashCode() : 0;
			result = 31 * result + (phone != null ? phone.hashCode() : 0);
			return result;
		}
	}
}
