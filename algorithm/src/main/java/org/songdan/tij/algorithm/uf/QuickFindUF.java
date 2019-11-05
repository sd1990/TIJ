package org.songdan.tij.algorithm.uf;

/**
 * quick find算法实现UF
 *
 * @author: Songdan
 * @create: 2019-11-03 11:44
 **/
public class QuickFindUF extends UF {


    public QuickFindUF(int n) {
        super(n);
    }

    @Override
    public int find(int i) {
        return id[i];
    }

    @Override
    public void union(int i, int j) {
        int iID = find(i);
        int jID = find(j);
        if (iID == jID) {
            return;
        }
        for (int k = 0; k < id.length; k++) {
            if (id[k] == iID) {
                id[k] = jID;
            }
        }
        count--;
    }
}
