package org.songdan.tij.reflect.annotation;

import javax.xml.bind.annotation.*;

/**
 * xml对应的bean
 *
 * @author Songdan
 * @date 2016/6/15
 */
@XmlRootElement(name = "person")
//@XmlType(propOrder = {"age","name","address"})
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Person {
    @XmlAttribute(name = "class")
    private String className;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "age")
    private int age;

//    @XmlElement(name = "address")
//    private Address address;

//    private List<Person> children;


    public void setClassName(String className) {
        this.className = className;
    }

//    @XmlElement(name = "child")
//    public void setChildren(List<Person> children) {
//        this.children = children;
//    }


    public void setName(String name) {
        this.name = name;
    }


//    public void setAddress(Address address) {
//        this.address = address;
//    }


    public void setAge(int age) {
        this.age = age;
    }

//    public String getClassName() {
//        return className;
//    }
//
//    public Address getAddress() {
//        return address;
//    }
//
//    public int getAge() {
//        return age;
//    }

//    public List<Person> getChildren() {
//        return children;
//    }

//    public String getName() {
//        return name;
//    }
}
