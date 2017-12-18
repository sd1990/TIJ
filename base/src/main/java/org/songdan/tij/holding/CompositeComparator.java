package org.songdan.tij.holding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 组合比较器
 *
 * @author song dan
 * @since 18 十二月 2017
 */
public class CompositeComparator<T> implements Comparator<T> {

    private Comparator<T> one;
    private Comparator<T> two;

    @SuppressWarnings("很安全")
    public CompositeComparator(Comparator<T>... comparators) {
        if (1 == comparators.length) {
            this.one = comparators[0];
            this.two = null;
        }
        if (2 == comparators.length) {
            this.one = comparators[0];
            this.two = comparators[1];
        } else {
            this.one = comparators[0];
            Comparator<T>[] dest = (Comparator<T>[]) new Comparator[comparators.length - 1];
            System.arraycopy(comparators, 1, dest, 0, comparators.length - 1);
            this.two = new CompositeComparator<>(dest);
        }

    }

    public static void main(String[] args) {
        List<Person> people = buildPerson();
        System.out.println(people);
        people = people.stream()
                .sorted(new CompositeComparator<>(Comparator.comparingInt(Person::getAge),
                        Comparator.comparing(Person::getName), Comparator.comparingInt(Person::getWeight)))
                .collect(Collectors.toList());
        System.out.println(people);

    }

    private static List<Person> buildPerson() {
        ArrayList<Person> people = new ArrayList<>(10);
        String[] names = { "zhangsan", "lisi", "wangwu" };
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setAge(new Random().nextInt(5));
            person.setName(names[new Random().nextInt(3)]);
            person.setWeight(new Random().nextInt(100));
            people.add(person);
        }
        return people;
    }

    public Comparator<T> buildComparator(Comparator<T>... comparators) {
        if (1 == comparators.length) {
            return comparators[0];
        }
        Comparator<T>[] dest = (Comparator<T>[]) new Comparator[comparators.length - 1];
        System.arraycopy(comparators, 1, dest, 0, comparators.length - 1);
        return new CompositeComparator<>(comparators[0], buildComparator(dest));
    }

    @Override
    public int compare(T o1, T o2) {
        int stepOne = one.compare(o1, o2);
        if (two == null) {
            return stepOne;
        }
        return stepOne == 0 ? two.compare(o1, o2) : stepOne;
    }
}
