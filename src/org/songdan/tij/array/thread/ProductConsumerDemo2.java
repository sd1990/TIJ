package org.songdan.tij.array.thread;

/**
 * 生产者消费者模型
 * @author SONGDAN
 *
 */
public class ProductConsumerDemo2 {
    /**
     * 共享资源
     * @author SONGDAN
     *
     */
    class Resource{
        /**生产开关*/
        private boolean flag=true;
        
        private String name;
        
        private int count;
        
        public synchronized void set(String name){
            if(!flag){
                try {
                    this.wait();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.name=name;
            this.count++;
            flag=false;
            System.out.println(Thread.currentThread().getName()+": 生产者----"+this.name+"----"+this.count);
            //唤醒消费者
            this.notifyAll();
        }
        public synchronized void get(){
            if(flag){
                try {
                    this.wait();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+": 消费者----"+this.name+"---"+this.count);
            flag=true;
            this.notifyAll();
        }
    }
    
    class Productor implements Runnable{

        private Resource resource;
        
        public Productor(Resource resource) {
            super();
            this.resource=resource;
        }

        @Override
        public void run() {
            for (; ; ) {
                resource.set("product--");
            }
        }
        
    }
    class Consumer implements Runnable{
        
        private Resource resource;
        
        public Consumer(Resource resource) {
            super();
            this.resource=resource;
        }
        
        @Override
        public void run() {
            for (; ; ) {
                resource.get();
            }
        }
        
    }
    
    public static void main(String[] args) {
        ProductConsumerDemo2.Resource r=new ProductConsumerDemo2().new Resource();
        Productor p=new ProductConsumerDemo2().new Productor(r);
        Consumer c=new ProductConsumerDemo2().new Consumer(r);
        Thread t1 = new Thread(p);
        Thread t2 = new Thread(p);
        Thread t4 = new Thread(c);
        Thread t5 = new Thread(c);
        t1.start();
        t2.start();
        t4.start();
        t5.start();
    }
}
