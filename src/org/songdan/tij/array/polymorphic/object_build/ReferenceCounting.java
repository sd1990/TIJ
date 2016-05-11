package org.songdan.tij.array.polymorphic.object_build;

/**
 * thinking in java 4th Chapter 8 exer13
 * @author SONGDAN
 *
 */
public class ReferenceCounting {
    public static void main(String[] args) {
        Shared share = new Shared();
        Compose[] comps=new Compose[]{
            new Compose(share),
            new Compose(share),
            new Compose(share),
            new Compose(share),
        };
        for (Compose compose : comps) {
            compose.dispose();
        }
        System.gc();
        new Compose(new Shared());
        System.gc();
    }
}

class Shared{
    /**静态变量，用来统计对象生成的次数*/
    private static int counter=0;
    
    /**用来统计被引用的次数*/
    private int refCount=0;
    
    /**对象的id，在生命周期中不能被修改*/
    private final int id = counter++;
    
    Shared(){
        System.out.println("create "+this);
    }
    
    public void addRefer(){
        refCount++;
    }
    public void dispose(){
        if(--refCount==0){
            System.out.println("shared object "+id+" clean run ..");
        }
    }

    @Override
    public String toString() {
        return "Shared " + id;
    }
    
    
}

class Compose{
    private static int counter = 0;
    private final int id = counter++;
    
    private Shared share;
    
    Compose(Shared share){
       System.out.println("composeing "+id);
       this.share=share;
       this.share.addRefer();
    }
    
    public void dispose(){
        System.out.println("disposing "+this);
        share.dispose();
    }
    public String toString(){
        return "Composing "+id;
    }
}















