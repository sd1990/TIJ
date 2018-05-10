package org.songdan.tij.algorithm.recursive;

import java.util.stream.LongStream;

import org.songdan.tij.algorithm.StopWatchTemplate;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 10 五月 2018
 */
public class Sum {

	public static void main(String[] args) {
		int longth = 100000000;
//		int[] arr = generate(longth);
		long[] longArr = generateLong(longth);
/*
		StopWatchTemplate<Long>.Result result = new StopWatchTemplate<Long>() {
			@Override
			protected Long doWork() {
				return calculate(arr);
			}
		}.time();
*/
		/*StopWatchTemplate<Long>.Result resultTail = new StopWatchTemplate<Long>() {
			@Override
			protected Long doWork() {
				return calculateTail(arr, 0);
			}
		}.time();*/
		StopWatchTemplate<Long>.Result nativeResult = new StopWatchTemplate<Long>() {
			@Override
			protected Long doWork() {
				return streamCalculate(longArr);
			}
		}.time();
//		StopWatchTemplate<Long>.Result nativeResultV2 = new StopWatchTemplate<Long>() {
//			@Override
//			protected Long doWork() {
//				return nativeCalculate(arr);
//			}
//		}.time();
//		System.out.println(result);
//		System.out.println(resultTail);
		System.out.println("stream:"+nativeResult);
//		System.out.println("native:"+nativeResultV2);
	}

	public static Long streamCalculate(long[] arr) {
		return LongStream.of(arr).sum();
	}

	public static Long nativeCalculate(int[] arr) {
		long i = 0;
		for (int j = 0; j < arr.length; j++) {
			i += arr[j];
		}
		return i;
	}


	public static Long calculate(int[] arr) {
		if (arr.length < 2) {
			return (long) arr[0];
		}
		int[] next = new int[arr.length - 1];
		System.arraycopy(arr, 1, next, 0, arr.length - 1);
		return arr[0] + calculate(next);
	}

	public static Long calculateTail(int[] arr, int sum) {
		int nextValue = arr[0] + sum;
		if (arr.length < 2) {
			return (long) nextValue;
		}
		int[] next = new int[arr.length - 1];
		System.arraycopy(arr, 1, next, 0, arr.length - 1);
		return calculateTail(next, nextValue);
	}

	private static int[] generate(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = i + 1;
		}
		return arr;
	}

	private static long[] generateLong(int n) {
		long[] arr = new long[n];
		for (int i = 0; i < n; i++) {
			arr[i] = i + 1;
		}
		return arr;
	}

}
