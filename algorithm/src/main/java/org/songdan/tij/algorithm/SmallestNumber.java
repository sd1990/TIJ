package org.songdan.tij.algorithm;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 22 三月 2018
 */
public class SmallestNumber {

	private int[] arr;

	private int nums;

	private int smallNums;

	private Set<Integer> books = new LinkedHashSet<>();

	public SmallestNumber(int nums, int smallNums) {
		this.nums = nums;
		this.smallNums = smallNums;
		init();
	}

	private void init() {
		this.arr = new int[nums];
		ThreadLocalRandom random = ThreadLocalRandom.current();
		for (int i = 0; i < nums; i++) {
			arr[i] = random.nextInt(nums * 100);
		}
		print();
	}

	private void print() {
		for (int i : arr) {
			System.out.print(i);
			System.out.print(",");
		}
		System.out.println();
	}

	public List<Integer> detect() {
		dfs(1);
		ArrayList<Integer> list = new ArrayList<>(smallNums);
		for (Integer index : books) {
			list.add(arr[index]);
		}
		return list;
	}

	private void dfs(int step) {
		if (step > smallNums) {
			return;
		}
		books.add(small(arr));
		dfs(++step);
	}

	private Integer small(int[] arr) {
		int index = -1;
		int small = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] <= small&&!books.contains(i)) {
				small = arr[i];
				index = i;
			}
		}
		return index;
	}

	public static void main(String[] args) {
		SmallestNumber smallestNumber = new SmallestNumber(10, 3);
		List<Integer> detect = smallestNumber.detect();
		System.out.println(detect);
	}
}
