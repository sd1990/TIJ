package org.songdan.tij.designpattern.factorymethod;


public class Unicycle extends Cycle{

    @Override
    public void move() {
        System.out.println("unicycle run ...");
    }
    
    private Unicycle(){
        
    }
    
    /**此处使用匿名内部类的方式实现*/
    public static CycleFactory factory = new CycleFactory(){

        @Override
        public Cycle getFactory() {
            return new Unicycle();
        }
        
    };
}
