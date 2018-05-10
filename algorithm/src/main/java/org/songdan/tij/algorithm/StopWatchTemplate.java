package org.songdan.tij.algorithm;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 10 五月 2018
 */
public abstract class StopWatchTemplate<T> {

	public class Result{
		private T result;

		private long time;

		private Result(T result, long time) {
			this.result = result;
			this.time = time;
		}

		public T getResult() {
			return result;
		}

		public long getTime() {
			return time;
		}

		@Override
		public String toString() {
			return "Result{" +
					"result=" + result +
					", time=" + time +
					'}';
		}
	}

	public Result time() {
		long start = System.currentTimeMillis();
		return new Result(doWork(), System.currentTimeMillis() - start);
	}

	protected abstract T doWork();




}
