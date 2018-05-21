package org.songdan.tij.algorithm;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 跳跃表
 *
 * @author song dan
 * @since 15 五月 2018
 */
public class SkipList {

	/**
	 * 跳跃表节点
	 */
	class Node{

		/**
		 * 前驱
		 */
		Node next;

		/**
		 * 向下
		 */
		Node down;

		int value;

	}

	private int generateLevel() {
		ThreadLocalRandom current = ThreadLocalRandom.current();
		int level = 1;
		while ((current.nextInt() & 1) == 0) {
			level++;
		}
		return level;
	}



}
