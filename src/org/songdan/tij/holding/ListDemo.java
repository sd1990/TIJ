package org.songdan.tij.holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
        /*Person p1 = new Person("zhangsan",10);
        Person p2 = new Person("lisi",11);
        List<Person> list = new ArrayList<Person>();
        list.add(p1);
        list.add(p2);
        Person p3 = new Person("zhangsan2",10);
        System.out.println(list.contains(p3));
        System.out.println(list.remove(p3));
        list.add(p3);
        Collections.shuffle(list);
        List<Person> subList = list.subList(0, 1);
        System.out.println(subList.get(0));
        System.out.println(list);
        Person p = null;
        for (int i = 0; i < 10; i++) {
            p=new Person("songdan"+i, i+10);
            list.add(p);
        }
        System.out.println(list);*/

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
}
