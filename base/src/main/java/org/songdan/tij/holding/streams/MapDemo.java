package org.songdan.tij.holding.streams;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Songdan
 * @date 2017/7/14 10:57
 */
public class MapDemo {

    public static void main(String[] args) {
        List<Integer> integers = buildOriginList();
        List<Integer> collect = integers.stream().map(element -> element * 2).collect(Collectors.toList());
        System.out.println(integers);
        System.out.println(collect);
        Person person1 = new Person();
        person1.setName("songdan");
        person1.setAge(1);
        Person person2 = new Person();
        person2.setName("songdan");
        person2.setAge(2);
        List<Person> list = Lists.newArrayList(person1, person2);
        Map<String, List<Person>> lgsAreaMap = list.stream().collect(Collectors.toMap(Person::getName, Lists::newArrayList, (x1, x2) -> {
            x1.addAll(x2);
            return x1;
        }));
        System.out.println(lgsAreaMap);
    }

    @Data
    @ToString
    public static class Person{
        private String name;
        private Integer age;
    }

    private static List<Integer> buildOriginList() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            result.add(i);
        }
        return result;
    }
}
