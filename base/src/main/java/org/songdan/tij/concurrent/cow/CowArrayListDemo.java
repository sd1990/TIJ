package org.songdan.tij.concurrent.cow;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 测试Cow并发容器
 *
 * @author song dan
 * @since 03 四月 2018
 */
public class CowArrayListDemo {

    private CopyOnWriteArrayList<Integer> copyOnWriteArrayList;

    public CowArrayListDemo() {
        copyOnWriteArrayList = new CopyOnWriteArrayList<>();
    }

    public static void main(String[] args) throws InterruptedException {
        CowArrayListDemo cowArrayListDemo = new CowArrayListDemo();
        CountDownLatch signal = new CountDownLatch(1);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
			try {
				print("waiting signal");
				signal.await();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			print("execute working");
			cowArrayListDemo.iterate(false);
			countDownLatch.countDown();
		}).start();
		new Thread(() -> {
			try {
				print("waiting signal");
				signal.await();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			print("execute working");
			cowArrayListDemo.add(false);
            countDownLatch.countDown();
        }).start();
        cowArrayListDemo.add(true);
        signal.countDown();
        print("waiting for worker over");
        countDownLatch.await();
        cowArrayListDemo.iterate(true);

    }

	private static void print(String content) {
		System.out.println(Thread.currentThread().getName()+"@"+ LocalDateTime.now()+":"+content);
	}

	public void iterate(boolean isQuiet) {
        Iterator<Integer> iterator = copyOnWriteArrayList.iterator();
        while (iterator.hasNext()) {
            if (!isQuiet) {
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            print("-> iterate :[" + iterator.next() + "];");
        }
    }

    private void add(boolean isQuiet) {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        for (int i = 0; i < 20; i++) {
            if (!isQuiet) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int num = current.nextInt(100);
            print("-> add :[" + num + "];");
            copyOnWriteArrayList.add(num);
        }
    }
}
