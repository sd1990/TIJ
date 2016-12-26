package org.songdan.tij.holding;

public class Person {

    private String name;

    private int age;

    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!Person.class.isInstance(obj)) {
            return false;
        }
        Person p = (Person) obj;
        if (hashCode() == p.hashCode()) {
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }

}
