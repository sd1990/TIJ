package org.songdan.tij.reflect;

interface HasBatteries{}

interface Waterproot{}

interface Shoots{}

class Toy{
    public Toy(){
        
    }
    public Toy(int i){
        
    }
}

class FancyToy extends Toy implements HasBatteries,Waterproot,Shoots{
    
    public FancyToy() {
        super(1);
    }
}

public class ToyTest {
    static void printInfo(Class cc){
        System.out.println("Class name: "+cc.getName() +"is interface?["+cc.isInterface()+"]");
        System.out.println("Class SimpleName name: "+cc.getSimpleName());
        System.out.println("Canocial name :"+cc.getCanonicalName());
    }
    
    public static void main(String[] args) {
        Class c=null;
        try {
            c = Class.forName("com.songdan.demo.reflect.FancyToy");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Can't find class FancyToy");
            System.exit(1);
        }
        printInfo(c);
        for(Class face:c.getInterfaces()){
            printInfo(face);
        }
        Class superclass = c.getSuperclass();
        Object obj=null;
        try {
            obj = superclass.newInstance();
        }
        catch (InstantiationException e) {
            System.out.println("cannot instance");
            System.exit(1);
        }
        catch (IllegalAccessException e) {
            System.out.println("Cannot access");
            System.exit(1);
        }
        printInfo(obj.getClass());
    }
}
