package org.songdan.tij.algorithm.uf;

/**
 * 实现quick union的UF算法,数组每个触点对应的元素都是同一个分量中另一个触点的名称，由它链接到另一个触点，直到到达一个根触点（触点和触点对应的值相等）
 * @author: Songdan
 * @create: 2019-11-03 11:54
 **/
public class QuickUnionUF extends UF{


    public QuickUnionUF(int n) {
        super(n);
    }

    @Override
    public int find(int i) {
        while (id[i] != i) {
            i = id[i];
        }
        return i;
    }

    @Override
    public void union(int i, int j) {
        int iID = find(i);
        int jID = find(j);
        if (iID == jID) {
            return;
        }
        id[iID] = jID;
        count--;
    }
}
