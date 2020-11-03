package org.songdan.tij.algorithm;

import java.util.LinkedList;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 28 四月 2018
 */
public class StackAndQueue {

	interface Queue<E>{
		void offer(E e);
		E poll();
		boolean isEmpty();
	}

	static class DefaultQueue<E> implements Queue<E>{

		private LinkedList<E> real = new LinkedList<>();

		public DefaultQueue() {
		}

		@Override
		public void offer(E o) {
			real.offer(o);
		}

		@Override
		public E poll() {
			return real.poll();
		}

		@Override
		public boolean isEmpty() {
			return real.isEmpty();
		}

		@Override
		public String toString() {
			return real.toString();
		}
	}

	interface Stack<E>{
		void push(E e);
		E pop();

		boolean isEmpty();
	}

	static class DefaultStack<E> implements Stack<E>{

		private LinkedList<E> real = new LinkedList<>();

		@Override
		public void push(E e) {
			real.addFirst(e);
		}

		@Override
		public E pop() {
			return real.removeFirst();
		}

		@Override
		public boolean isEmpty() {
			return real.isEmpty();
		}

		@Override
		public String toString() {
			return real.toString();
		}
	}

	/**
	 * Stack Implements Queue(FIFO)
	 */
	public static class Stack2Queue<E> implements Queue<E>{

		private Stack<E> first = new DefaultStack<>();
		private Stack<E> second = new DefaultStack<>();


		@Override
		public void offer(E e) {
			first.push(e);
		}

		@Override
		public E poll() {
			while (!first.isEmpty()) {
				second.push(first.pop());
			}
			E pop = second.pop();
			while (!second.isEmpty()) {
				first.push(second.pop());
			}
			return pop;
		}

		@Override
		public boolean isEmpty() {
			return first.isEmpty();
		}

		@Override
		public String toString() {

			return first.toString();
		}
	}

	public static class Stack2QueueV2<E> implements Queue<E>{

		private Stack<E> first = new DefaultStack<>();
		private Stack<E> second = new DefaultStack<>();


		@Override
		public void offer(E e) {
			first.push(e);
		}

		@Override
		public E poll() {
			if (second.isEmpty()) {
				while (!first.isEmpty()) {
					second.push(first.pop());
				}
			}
			return second.pop();
		}

		@Override
		public boolean isEmpty() {
			return first.isEmpty();
		}

		@Override
		public String toString() {

			return first.toString();
		}
	}

	public static class Queue2Stack<E> implements Stack<E> {

		private Queue<E> first = new DefaultQueue<>();
		private Queue<E> second = new DefaultQueue<>();

		@Override
		public void push(E e) {
			first.offer(e);
		}

		@Override
		public E pop() {
			E e = null;
			while (!first.isEmpty()) {
				e = first.poll();
				if (!first.isEmpty()) {
					second.offer(e);
				}
			}
			swap();
			return e;
		}

		@Override
		public boolean isEmpty() {
			return first.isEmpty();
		}

		@Override
		public String toString() {
			return first.toString();
		}

		private void swap() {
			Queue<E> temp = first;
			first = second;
			second = temp;
		}


	}


	public static void main(String[] args) {
		Stack2Queue<Integer> stack2Queue = new Stack2Queue<>();
		stack2Queue.offer(1);
		stack2Queue.offer(2);
		stack2Queue.offer(3);
		System.out.println(stack2Queue.poll());
		stack2Queue.offer(4);
		while (!stack2Queue.isEmpty()) {
			System.out.println(stack2Queue.poll());
		}
		System.out.println("==============");
		Stack2QueueV2<Integer> stack2QueueV2 = new Stack2QueueV2<>();
		stack2QueueV2.offer(1);
		stack2QueueV2.offer(2);
		stack2QueueV2.offer(3);
		System.out.println(stack2QueueV2.poll());
		stack2QueueV2.offer(4);
		while (!stack2QueueV2.isEmpty()) {
			System.out.println(stack2QueueV2.poll());
		}
		System.out.println("==============");
		Queue2Stack<Integer> stack = new Queue2Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		System.out.println(stack.pop());
		stack.push(4);
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}

}
