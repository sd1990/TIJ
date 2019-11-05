package org.songdan.tij.algorithm.uf;

/**
 * 加权quick union算法
 *
 * @author: Songdan
 * @create: 2019-11-03 12:01
 **/
public class WeightQuickUnionUF extends QuickUnionUF {

    private int[] weight;

    public WeightQuickUnionUF(int n) {
        super(n);
        for (int i = 0; i < n; i++) {
            weight[i] = 1;
        }
    }


    @Override
    public void union(int i, int j) {
        int iRoot = find(i);
        int jRoot = find(j);
        if (iRoot==jRoot) {
            return;
        }
        int iWeight = weight[iRoot];
        int jWeight = weight[jRoot];
        if (iWeight < jWeight) {
            id[iRoot] = jRoot;
            weight[jRoot] += weight[iRoot];
        } else {
            id[jRoot] = iRoot;
            weight[iRoot] += weight[jRoot];
        }
        count--;
    }
}
