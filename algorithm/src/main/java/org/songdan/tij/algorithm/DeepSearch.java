package org.songdan.tij.algorithm;

/**
 * 数字的全排列
 *
 * @author song dan
 * @since 30 十一月 2017
 */
public class DeepSearch {

	private int n;

	/** 用于标记哪一个数字被使用了 */
	private int[] book;

	private int[] bucks;

	public DeepSearch(int n) {
		this.n = n;
		book = new int[n+1];
		bucks = new int[n+1];
	}

	public void dfs(int step) {
		//所有的位置都放置了数字，退出
		if (step > n) {
			StringBuilder builder = new StringBuilder();
			for (int buck : bucks) {
				builder.append(buck).append(",");
			}
			System.out.println(builder.toString());
			return;
		}
		for (int i = 1; i <= n; i++) {
			if (book[i] == 0) {
				//如果数字i没有被使用，将i放到第step的位置
				bucks[step] = i;
				//标示被占用了
				book[i] = 1;
				//执行下一步
				dfs(step+1);
				//回收i
				book[i] = 0;
			}
		}
	}

	public static void main(String[] args) {
		DeepSearch deepSearch = new DeepSearch(4);
		deepSearch.dfs(1);

	}

}
