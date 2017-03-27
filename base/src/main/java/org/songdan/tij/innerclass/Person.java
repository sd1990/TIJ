package org.songdan.tij.innerclass;

public class Person {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + "]";
    }

    public static void main(String[] args) {
        Sequence s = new Sequence(10);
        Person p = new Person();
        p.setName("songdan");
        s.add(p);
        Selector selector = s.selector();
        while (selector.end()) {
            System.out.println(selector.next());
        }
    }
}
