package org.songdan.tij.algorithm;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 数组算法
 *
 * @author song dan
 * @since 20 十一月 2017
 */
public interface ArraySortAlgorithms {

	void sort(int[] arr);

	class ArrayUtil{

		static void swap(int[] arr, int i, int j) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}

		static String toString(int[] arr) {

			StringBuilder builder = new StringBuilder();
			for (int i : arr) {
				builder.append(i).append(",");
			}
			String result = builder.toString();
			return result.substring(0,result.length()-1);

		}


	}

	public static class WrongQuickSortAlgorithms implements ArraySortAlgorithms{

		@Override
		public void sort(int[] arr) {
			quickSort(arr, 0, arr.length - 1);
		}

		private void quickSort(int[] arr, int left, int right) {
			if (left >= right) {
				return;
			}
			int contrast = arr[left];
			int i = left;
			int j = right;
			while (i != j) {
				//错误写法，从左边开始，在即将进行外层交换的时候，会遇到大于contrast的数字，将这个数字和contrast交换，违背了排序的原则
				for (;i<j && arr[i] <= contrast; i++) {

				}
				for (;i<j && arr[j] >= contrast;j--) {

				}
				if (i < j) {
					ArrayUtil.swap(arr, i, j);
					System.out.println(ArrayUtil.toString(arr));
				}
			}
			//交换i和contrat
			System.out.println("=======swap======");
			ArrayUtil.swap(arr, left, i);
			System.out.println(ArrayUtil.toString(arr));
			//分治
			quickSort(arr, 0, i - 1);
			quickSort(arr, i+1, left);
		}


	}

	public static void main(String[] args) {
		ArraySortAlgorithms algorithms = new WrongQuickSortAlgorithms();
		int[] arr = generate();
		System.out.println(ArrayUtil.toString(arr));
		algorithms.sort(arr);
		System.out.println(ArrayUtil.toString(arr));

	}

	static int[] generate() {
		int[] arr = new int[10];
		ThreadLocalRandom rand = ThreadLocalRandom.current();
		for (int i = 0; i < 10; i++) {
			arr[i] = rand.nextInt(10);
		}
		return arr;
	}

}
