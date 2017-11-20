package org.songdan.tij.thread.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 15 十一月 2017
 */
public class CyclicBarrierDemo {

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			barrier();
			System.out.println();
		}
	}

	private static void barrier() {
		CyclicBarrier barrier = new CyclicBarrier(2,()->{
			System.out.print(3);
		});
		new Thread(()->{
			try {
				barrier.await();
			}
			catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.print(1);
		}).start();
		try {
			barrier.await();
		}
		catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		System.out.print(2);
	}

}
