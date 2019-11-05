package org.songdan.tij.algorithm.stack;

import java.util.*;

/**
 *
 * 一副从1到n的牌，每次从牌堆顶取一张放桌子上，再取一张放牌堆底，直到手机没牌，最后桌子上的牌是从1到n有序，设计程序，输入n，输出牌堆的顺序数组
 * 我的理解：问题的本质相当于对一组数据按照特定规则排序，然后获取排序前的数组原貌
 * vczh:直接把一副1..n的牌那样玩一遍，结果就是索引值，然后把桌子上的牌堆按照索引值排序（这里是O(n)），就得到原来的结果了
 * 为什么直接排好序的结果再玩一遍结果就是索引值？
 * 拿一堆数字的下标去排序，排完后依次填入1到n，然后读原数组
 * 参考答案{@link https://zhuanlan.zhihu.com/p/38850888}
 * @author song dan
 * @since 28 四月 2018
 */
public class Xiaomi {


	public void out(Queue<Integer> queue,Queue<Integer> desk) {
		if (queue.size() == 1) {
			desk.offer(queue.poll());
			return;
		}
		desk.offer(queue.poll());
		queue.offer(queue.poll());
		out(queue,desk);
	}

	public void reverse(LinkedList<Integer> desk,LinkedList<Integer> queue) {
		if (desk.isEmpty()) {
			return;
		}
		if (queue.peekLast()!=null) {
			queue.offerFirst(queue.pollLast());
		}
		if (desk.peekLast() != null) {
			queue.offerFirst(desk.pollLast());
		}
		reverse(desk,queue);
	}

	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<>();
		Queue<Integer> desk = new LinkedList<>();
		queue.offer(1);
		queue.offer(2);
		queue.offer(3);
		queue.offer(4);
		queue.offer(5);
		Xiaomi xiaomi = new Xiaomi();
		xiaomi.out(queue,desk);
		System.out.println(desk);
		int[] arr = new int[6];
		int i = 0;
		while (!desk.isEmpty()) {
			arr[desk.poll()] = i + 1;
			i++;
		}


		for (int j = 1; j < arr.length; j++) {
			queue.offer(arr[j]);
		}
		System.out.println("origin queue is :");
		System.out.println(queue);
		//验证结果
		xiaomi.out(queue,desk);
		System.out.println(desk);
		System.out.println("===============");

		LinkedList<Integer> reQueue = new LinkedList<>();
		LinkedList<Integer> reDesk = new LinkedList<>();
		reDesk.offer(1);
		reDesk.offer(2);
		reDesk.offer(3);
		reDesk.offer(4);
		reDesk.offer(5);
		xiaomi.reverse(reDesk,reQueue);
		System.out.println(reQueue);
		xiaomi.out(reQueue,reDesk);
		System.out.println(reDesk);


	}

}
