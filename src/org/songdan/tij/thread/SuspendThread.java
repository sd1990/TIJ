package org.songdan.tij.thread;


public class SuspendThread implements Runnable{
    
    private volatile int firstValue=0;
    private volatile int secondValue=0;
    private volatile boolean suspended;
    @Override
    public void run() {
        suspended=false;
        try {
            workMethod();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private void workMethod() throws InterruptedException {
        int value=1;
        while(true){
            waitWhileSleep();
            setFirstValue(value);
            setSecondValue(value);
            value++;
            waitWhileSleep();
            Thread.sleep(100l);
        }
            
    }

    private void setSecondValue(int value) {
        secondValue=value;
    }

    private void setFirstValue(int value) throws InterruptedException {
        firstValue=value;
        Thread.sleep(100l);
    }

    private void waitWhileSleep() throws InterruptedException {
        if(suspended){
            Thread.sleep(200l);
        }
    }

    public boolean areEqual(){
        return firstValue==secondValue;
    }
    
    
    public static void main(String[] args) throws InterruptedException {
        SuspendThread st=new SuspendThread();
        Thread t=new Thread(st);
        t.start();
        try {
            Thread.sleep(1000l);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 100; i++) {
            st.suspended=true;
            Thread.sleep(500);
            System.out.println(st.areEqual());
            st.suspended=false;
            Thread.sleep(  
                    ( long ) (Math.random() * 2000.0) );
        }
        System.exit(0);
    }
}
