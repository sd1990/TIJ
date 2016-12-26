package org.songdan.tij.oom;

import org.songdan.tij.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * 此类为了证明当一个局部变量引用了一个GC Root变量时，当局部变量所在的方法执行完成后，局部变量依然可以被垃圾回收，不会产生内存泄漏；然而当GC Root变量保存了局部变量，即使使用局部变量的方法执行完成，局部变量不会被垃圾回收，产生内存泄漏
 * Created by SongDan on 2016/12/3.
 */
public class GCObject {

    private Person person = new Person();

    private List<PersonContainer> list = new ArrayList<>();

    public static void main(String[] args) {
        GCObject gcObject = new GCObject();
        while (true) {
            gcObject.useLocal();
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void useLocal() {
        PersonContainer personContainer = new PersonContainer(person);
        //        System.out.println("using personContainer:" + personContainer);
        list.add(personContainer);
    }

}

class PersonContainer {

    private Person person;

    public PersonContainer(Person person) {
        this.person = person;
    }
}
