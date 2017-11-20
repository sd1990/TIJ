package org.songdan.tij.algorithm;

/**
 * 数组算法
 *
 * @author song dan
 * @since 20 十一月 2017
 */
public interface ArraySortAlgorithms {

	void sort(int[] arr);

	static class ArrayUtil{

		static void swap(int[] arr, int i, int j) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}

	}

	public static class QuickSortAlgorithms implements ArraySortAlgorithms{

		@Override
		public void sort(int[] arr) {
			quickSort(arr, 0, arr.length - 1);
		}

		private void quickSort(int[] arr, int left, int right) {
			int contrast = arr[left];
			int i = left;
			int j = right;
			while (i != j) {
				for (;i<j && arr[i] < contrast; i++) {

				}
				for (;i<j && arr[j] > contrast;j--) {

				}
				if (i < j) {
					ArrayUtil.swap(arr, i, j);
				}
			}
			//交换i和contrat
			ArrayUtil.swap(arr, left, i);
			//分治
			quickSort(arr, 0, i - 1);
			quickSort(arr, i+1, left);
		}


	}

	public static void main(String[] args) {
		ArraySortAlgorithms algorithms = new QuickSortAlgorithms();
		int[] arr = generate();
		algorithms.sort(arr);
		
	}

	private static int[] generate() {
		return null;
	}

}
