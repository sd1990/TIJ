package org.songdan.tij.holding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ArrayList 并发排序
 *
 * @author song dan
 * @since 24 九月 2017
 */
public class ArrayListSortConcurrently {

	public static void main(String[] args) {
		ArrayList<Integer> integers = buildList();
		/*此处的排序：第一是了模拟线上问题的场景
		* 第二 如果不排序就会在排序的过程中抛出java.lang.IllegalArgumentException: Comparison method violates its general contract!
		* 这个问题也是由于并发导致的
		*/
		integers.sort(null);
		CountDownLatch countDownLatch = new CountDownLatch(1);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
		for (int i = 0; i < 2; i++) {
//			executor.execute(new ExceptionCode(countDownLatch,integers));
			executor.execute(new ImproveCode(countDownLatch,integers));
		}
		countDownLatch.countDown();
		try {
			executor.shutdown();
			executor.awaitTermination(1, TimeUnit.MINUTES);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 异常代码
	 */
	static class ExceptionCode implements Runnable{

		private CountDownLatch latch;

		private List<Integer> list;

		public ExceptionCode(CountDownLatch latch, List<Integer> list) {
			this.latch = latch;
			this.list = list;
		}

		@Override
		public void run() {
			try {
				latch.await();
				Collections.sort(list);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 改进后的代码
	 */
	static class ImproveCode implements Runnable{

		private CountDownLatch latch;

		private List<Integer> list;

		public ImproveCode(CountDownLatch latch, List<Integer> list) {
			this.latch = latch;
			this.list = new ArrayList<>(list);
		}

		@Override
		public void run() {
			try {
				latch.await();
				Collections.sort(list);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}



	private static ArrayList<Integer> buildList() {
		ArrayList<Integer> integers = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 100000; i++) {
			integers.add(random.nextInt(100000));
		}
		return integers;
	}

}
