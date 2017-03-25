package org.songdan.tij.algorithm.polynomial;

/**
 * 表示多项式的数据结构
 * Created by SongDan on 2017/2/7.
 */
public class Polynomial {

    /**
     * 系数
     */
    private double coefficient;

    /**
     * 幂
     */
    private int power;

    public Polynomial(double coefficient, int power) {
        this.coefficient = coefficient;
        this.power = power;
    }

    public Polynomial() {
    }

    public double getCoefficient() {
        return coefficient;
    }

    public int getPower() {
        return power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Polynomial that = (Polynomial) o;

        if (Double.compare(that.coefficient, coefficient) != 0)
            return false;
        if (power != that.power)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(coefficient);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + power;
        return result;
    }

    @Override
    public String toString() {
        return "("+coefficient+"-"+power+")";
    }
}
