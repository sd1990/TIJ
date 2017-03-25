package org.songdan.tij.algorithm.polynomial;

import java.util.Comparator;

/**
 * Created by SongDan on 2017/2/7.
 */
public class PolynomialCompartor implements Comparator<Polynomial> {

    @Override
    public int compare(Polynomial o1, Polynomial o2) {
        return o1.getPower() > o2.getPower() ? 1 : (o1.getPower() < o2.getPower() ? -1 : 0);
    }
}
