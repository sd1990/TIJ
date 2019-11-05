package org.songdan.tij.algorithm.uf;

/**
 * union find 连通性问题
 *
 * @author: Songdan
 * @create: 2019-11-03 11:35
 **/
public abstract class UF {

    /**
     * 以触点为索引，分量为对应索引的值
     */
    protected int[] id;

    /**
     * 分量的数量
     */
    protected int count;

    public UF(int n){
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    /**
     * 触点i所在的分量
     * @param i
     * @return
     */
    public abstract int find(int i);

    /**
     * 将i，j连通到一个分量
     * @param i
     * @param j
     */
    public abstract void union(int i, int j);

    /**
     * 判断i，j是不是连通的，即判断i所在的分量和j所在的分量是不是同一个分量
     * @param i
     * @param j
     * @return
     */
    public boolean connected(int i,int j){
        return find(i) == find(j);
    }

    /**
     * 连通分量的数量
     * @return
     */
    public int count() {
        return count;
    }

}
