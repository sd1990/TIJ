package org.songdan.tij.array.innerclass;


public class Outer {
    private String flag;
    
    public Outer(String flag) {
        super();
        this.flag = flag;
    }

    class Inner{

        public Inner() {
            super();
            System.out.println("inner constructor run ...");
        }
        
        @Override
        public String toString() {
            return flag;
        }
    }
    
    public Inner getInnerInstance(){
        return new Inner();
    }
    
    public static void main(String[] args) {
        Outer out = new Outer("zhangfei");
        Inner innerInstance = out.getInnerInstance();
        System.out.println(innerInstance);
    }
}
