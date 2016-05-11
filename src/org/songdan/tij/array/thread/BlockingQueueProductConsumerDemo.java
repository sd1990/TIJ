package org.songdan.tij.array.thread;

import org.songdan.tij.array.random.RandomUtil;

import java.util.concurrent.*;

/**
 * @author Songdan
 * @date 2016/5/9
 */
public class BlockingQueueProductConsumerDemo {

    public static void main(String[] args) throws InterruptedException {
        Repository<String> repository = new Repository<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Thread(new Producer(repository)));
        executorService.execute(new Thread(new Consumor(repository)));
        TimeUnit.SECONDS.sleep(3);
        System.out.println("stop threads");
        executorService.shutdownNow();
    }
}

class Repository<E> {

    private BlockingQueue<E> queue = new ArrayBlockingQueue<>(10);

    /**
     * 获取库存
     *
     * @return
     */
    public int size() {
        return queue.size();
    }

    public E get() throws InterruptedException {
        return queue.take();
    }

    public void store(E o) throws InterruptedException {
        queue.put(o);
    }
}

class Producer implements Runnable {

    private Repository<String> repository;

    public Producer(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String o = String.valueOf(RandomUtil.getRandom16String());
                System.out.println(Thread.currentThread() + "producer " + o);
                repository.store(o);
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e) {
            System.out.println("consumer thread stop");
            Thread.currentThread().interrupt();
        }
    }
}

class Consumor implements Runnable {

    private Repository<String> repository;

    public Consumor(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void run() {
        try {
            String o = "";
            while ((o = repository.get())!=null) {
                System.out.println(Thread.currentThread() + "consumer " + o);
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e) {
            System.out.println("consumer thread stop");
            Thread.currentThread().interrupt();
        }
    }
}