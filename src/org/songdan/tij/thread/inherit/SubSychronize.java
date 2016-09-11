package org.songdan.tij.thread.inherit;

/**
 * @author Songdan
 * @date 2016/8/9
 */
public class SubSychronize extends SuperSynchronize {

    SuperSynchronize superSynchronize;

    public SubSychronize(SuperSynchronize superSynchronize) {
        this.superSynchronize = superSynchronize;
    }

    @Override
    public synchronized void run() {
//        superSynchronize.run();
        super.run();
        System.out.println("child run ...");
    }

    public static void main(String[] args) {
        final SuperSynchronize superSynchronize = new SuperSynchronize();
        final SuperSynchronize father = new SubSychronize(superSynchronize);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread 1 run ....");
                superSynchronize.run();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread 2 run ....");
                father.run();
            }
        }).start();
    }
}
