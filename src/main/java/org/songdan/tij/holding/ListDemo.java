package org.songdan.tij.holding;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class ListDemo {

    /**
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("aaa", "abc", "bbb", "aaa", "fds"));
        List<String> copyList = new ArrayList<>(list);
        for (String string : copyList) {
            System.out.println(copyList.size());
            if ("aaa".equalsIgnoreCase(string) || "bbb".equalsIgnoreCase(string)) {
                list.remove(string);
                string = null;
            }

        }
        System.out.println(list);
        System.out.println(copyList);
        testReference();
        testQueueReference();
        //        System.out.println(remove(list));
    }

    public static ArrayList<String> remove(ArrayList<String> list) {
        if (list == null || list.size() == 0) {
            return list;
        }
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String ele = iterator.next();
            if ("aaa".equalsIgnoreCase(ele) || "bbb".equalsIgnoreCase(ele)) {
                iterator.remove();
            }
        }
        return list;
    }

    public static void testReference() {
        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        person.setName("songdan");
        persons.add(person);
        System.out.println(persons);
        person.setName("liuyong");
        System.out.println(persons);
    }

    public static void testQueueReference() throws InterruptedException {
        LinkedBlockingQueue<Person> persons = new LinkedBlockingQueue<>();
        Person person = new Person();
        person.setName("songdan");
        persons.put(person);
        System.out.println(persons);
        person.setName("liuyong");
        System.out.println(persons);
    }
}
