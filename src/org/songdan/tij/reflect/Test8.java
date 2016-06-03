package org.songdan.tij.reflect;


public class Test8 {
    public static void printClass(Object obj){
        Class<? extends Object> class1 = obj.getClass();
        ToyTest.printInfo(class1);
        if(class1.getSuperclass()!=null){
            try {
                printClass(class1.getSuperclass().newInstance());
            }
            catch (InstantiationException e) {
                e.printStackTrace();
                System.exit(1);
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
    
    public static void main(String[] args) {
        Toy toy = new FancyToy();
        printClass(toy);
    }
}
