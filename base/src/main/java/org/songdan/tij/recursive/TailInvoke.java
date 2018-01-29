package org.songdan.tij.recursive;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 29 一月 2018
 */
public class TailInvoke {

	/**
	 * 获取下一个递归
	 * @param nextFrame
	 * @param <T>
	 * @return
	 */
	public static <T> TailRecursion<T> call(final TailRecursion<T> nextFrame){

		return nextFrame;

	}

	/**
	 * 获取最后一个递归元素
	 * @param value
	 * @param <T>
	 * @return
	 */
	public static <T> TailRecursion<T> done(final T value) {
		return new TailRecursion<T>() {
			@Override
			public TailRecursion<T> apply() {
				throw new RuntimeException("递归已经结束");
			}

			@Override
			public boolean isFinish() {
				return true;
			}

			@Override
			public T getResult() {
				return value;
			}

		};
	}

}
