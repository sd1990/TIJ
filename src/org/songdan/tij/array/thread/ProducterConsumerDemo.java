package org.songdan.tij.array.thread;

/*
 * 线程模拟生产者与消费者 要求：生产一个消费一个
 */
public class ProducterConsumerDemo {

    public static void main(String[] args) {
        Resource r = new Resource(); // 定义商品资源
        Producter pro = new Producter(r);// 创建生产者对象
        Consumer son = new Consumer(r); // 创建消费者对象

        // 创建两个生产者即两个线程
        Thread t1 = new Thread(pro);
        Thread t2 = new Thread(pro);

        // 创建两个消费者即两个线程
        Thread t3 = new Thread(son);
        Thread t4 = new Thread(son);

        // 启动四个线程并执行相应的run方法
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}

/*
 * 定义商品类
 */
class Resource {

    private String name; // 商品的名字

    private int count = 1; // 商品的编号

    private boolean flag = false;

    public synchronized void set(String name) {
        while (flag)
            try {
                this.wait();
            }
            catch (Exception e) {
            }
        this.name = name + "--" + count++;//
        System.out.println(Thread.currentThread().getName() + "...生产者 " + this.name);
        flag = true;
        this.notifyAll();
    }

    public synchronized void out() {
        while (!flag)
            try {
                this.wait();
            }
            catch (Exception e) {
            }
        System.out.println(Thread.currentThread().getName() + "...消费者————" + this.name);
        flag = false;
        this.notifyAll();
    }
}

// 商品的生产者
class Producter implements Runnable {

    private Resource res;

    Producter(Resource res) {
        this.res = res;
    }

    public void run() {
        while (true) {
            res.set("商品+");
        }
    }
}

// 商品的消费者
class Consumer implements Runnable {

    private Resource res;

    Consumer(Resource res) {
        this.res = res;
    }

    public void run() {
        while (true) {
            res.out();
        }
    }
}
