package org.songdan.tij.reflect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.songdan.tij.model.Person;

/**
 * deep copy
 * 不支持深copy相互引用的对象
 * @author song dan
 * @since 06 十二月 2017
 */
public class DeepCopyUtils {

    public static Object deepCopy(Object t) {
        if (t == null) {
            return t;
        }
        if (List.class.isAssignableFrom(t.getClass())) {
            return copyList((List)t);
        }
        else if (Set.class.isAssignableFrom(t.getClass())) {
            return copySet((Set)t);
        }
        else if (Map.class.isAssignableFrom(t.getClass())) {
            return copyMap((Map)t);
        }
        else {
            return ReflectionUtils.deepCopy(t);
        }
    }

    /**
     * 对List进行深copy
     * @param collection
     * @return
     */
    public static List copyList(List collection) {
        Iterator iterator = collection.iterator();
        if (collection.getClass().isInstance(LinkedList.class)) {
            LinkedList objects = new LinkedList();
            while (iterator.hasNext()) {
                Object next = iterator.next();
                objects.add(deepCopy(next));
            }
            return objects;
        } else {
            ArrayList objects = new ArrayList(collection.size());
            while (iterator.hasNext()) {
                Object next = iterator.next();
                objects.add(deepCopy(next));
            }
            return objects;
        }
    }

    /**
     * 对集合进行深copy
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> Set<? extends T> copySet(Set<? extends T> collection) {
        Iterator<? extends T> iterator = collection.iterator();
        if (collection.getClass().isInstance(LinkedHashSet.class)) {
            LinkedHashSet<T> objects = new LinkedHashSet<>();
            while (iterator.hasNext()) {
                T next = iterator.next();
                objects.add(next);
            }
            return objects;
        } else {
            HashSet<T> objects = new HashSet<T>(collection.size());
            while (iterator.hasNext()) {
                T next = iterator.next();
                objects.add(next);
            }
            return objects;
        }
    }

    /**
     * 对集合进行深copy
     * @param map
     * @return
     */
    public static Map copyMap(Map map) {
        Set entrySet = map.entrySet();
        if (map.getClass().isInstance(LinkedHashMap.class)) {
            LinkedHashMap objects = new LinkedHashMap<>();
            Iterator iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Map.Entry next = (Map.Entry) iterator.next();
                map.put(deepCopy(next.getKey()), deepCopy(next.getValue()));
            }
            return objects;
        } else {
            HashMap objects = new HashMap<>();
            Iterator iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Map.Entry next = (Map.Entry) iterator.next();
                map.put(deepCopy(next.getKey()), deepCopy(next.getValue()));
            }
            return objects;
        }
    }

    public static void main(String[] args) {
        System.out.println(List.class.getInterfaces()[0]);
        Person xiaonuan = new Person();
        xiaonuan.setName("xiaonuan");
        xiaonuan.setAge(1);
        Person father = new Person();
        father.setName("songdan");
        father.setAge(27);
        xiaonuan.setFather(father);
        Person friend = new Person();
        friend.setName("xixi");
        friend.setAge(2);
        xiaonuan.addFriend(friend);

        Person copy = (Person) deepCopy(xiaonuan);
        System.out.println(copy== xiaonuan);
        System.out.println(copy.equals(xiaonuan));
        /*Person copy = DeepCopyUtils.deepCopy(xiaonuan);
        System.out.println(copy== xiaonuan);
        System.out.println(copy.equals(xiaonuan));*/
    }


    static List<Person> create() {
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            Person person = new Person();
            person.setAge(10+i);
            person.setName("zhangsan" + i);
            personList.add(person);
        }
        return personList;
    }

    static Set<Person> createSet() {
        Set<Person> personSet = new HashSet<>();
        for (int i = 0; i < 10; i++) {

            Person person = new Person();
            person.setAge(10+i);
            person.setName("zhangsan" + i);
            personSet.add(person);
        }
        return personSet;
    }

}
