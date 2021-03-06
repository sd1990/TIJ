package org.songdan.tij.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Person extends Object implements Serializable {

    private String name;

    private Integer age;

    private Person father;

    private List<Person> friends = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public void addFriend(Person friend) {
        if (this.friends.contains(friend)) {
            return;
        }
        this.friends.add(friend);
//        friend.addFriend(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((age == null) ? 0 : age.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (age == null) {
            if (other.age != null)
                return false;
        }
        else if (!age.equals(other.age))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        HashSet<Person> hashSet = new HashSet<>();
        Person p1 = new Person();
        p1.setAge(1);
        p1.setName("zhangsan");
        System.out.println(p1.hashCode());
        Person p2 = new Person();
        p2.setAge(1);
        p2.setName("zhangsan");
        hashSet.add(p1);
        hashSet.add(p1);
        for (Person person : hashSet) {
            System.out.println(person.hashCode());
        }
    }
}
