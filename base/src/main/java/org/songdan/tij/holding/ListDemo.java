package org.songdan.tij.holding;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * List的常见操作，主要讨论remove操作
 */
public class ListDemo {

    public static void main(String[] args) throws InterruptedException {
//        System.out.println("==================================");
//        remove();
//        System.out.println("==================================");
//        removeByInverseOrder();
//        System.out.println("==================================");
//        removeByDoubleList();
//        System.out.println("==================================");
//        removeByCopyOnWriteArrayList();
//        System.out.println("==================================");
//        errorRemove();

        Person songdan = new Person("songdan", 21);
        ArrayList<Person> personList = new ArrayList<>();
        personList.add(songdan);
        personList.add(new Person("dengke", 23));
        testReverse(personList);
//        System.out.println(personList);

    }

    public static void errorRemove(){
        List<String> list = new ArrayList<>(initList());
        System.out.print("before remvoe:");
        System.out.println(list);
        for (String s : list) {
            if (isDelete(s)) {
                list.remove(s);
            }
        }
        System.out.print("after remvoe:");
        System.out.println(list);
    }

    /**
     * 迭代器删除元素
     * @return
     */
    public static void remove() {
        List<String> list = new ArrayList<>(initList());
        System.out.print("before remvoe:");
        System.out.println(list);

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String ele = iterator.next();
            if (isDelete(ele)) {
                iterator.remove();
            }
        }
        System.out.print("after remvoe:");
        System.out.println(list);
    }

    /**
     * 通过两个ArrayList来实现在遍历的时候进行删除操作
     * 可以理解为CopyOnWriteArrayList的实现
     */
    public static void removeByDoubleList() {
        ArrayList<String> list = new ArrayList<>(initList());
        List<String> copyList = new ArrayList<>(list);
        System.out.print("before remvoe:");
        System.out.println(list);
        for (String string : copyList) {
            if (isDelete(string)) {
                list.remove(string);
            }

        }
        System.out.print("after remvoe:");
        System.out.println(list);
    }

    public static List<String> initList() {
        return Arrays.asList("aaa", "abc", "bbb", "aaa", "fds");
    }

    /**
     * 倒序删除元素
     * @return
     */
    public static void removeByInverseOrder() {
        List<String> list = new ArrayList<>(initList());
        System.out.print("before remvoe:");
        System.out.println(list);
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String ele = iterator.next();
            if (isDelete(ele)) {
                iterator.remove();
            }
        }
        System.out.print("after remvoe:");
        System.out.println(list);
    }

    public static boolean isDelete(String ele) {
        return "aaa".equalsIgnoreCase(ele) || "bbb".equalsIgnoreCase(ele);
    }

    /**
     * 通过CopyOnArrayList实现遍历的时候删除元素
     */
    public static void removeByCopyOnWriteArrayList() {
        List<String> cowList = new CopyOnWriteArrayList<>(initList());
        System.out.print("before remvoe:");
        System.out.println(cowList);
        for (String s : cowList) {
            if (isDelete(s)) {
                cowList.remove(s);
            }
        }
        System.out.print("after remvoe:");
        System.out.println(cowList);
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

    public static <T> void testReverse(List<T> list) {
        ListIterator<T> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            T previous = listIterator.previous();
            System.out.println(previous);
        }
    }
}
