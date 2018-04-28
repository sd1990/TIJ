package org.songdan.tij.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 *
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

	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<>();
		Queue<Integer> desk = new LinkedList<>();
		queue.offer(1);
		queue.offer(2);
		queue.offer(3);
		queue.offer(4);
		queue.offer(5);
		new Xiaomi().out(queue,desk);
		System.out.println(desk);
		new Xiaomi().out(desk,queue);
		System.out.println(queue);
	}

}
