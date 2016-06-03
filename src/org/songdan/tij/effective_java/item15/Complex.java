package org.songdan.tij.effective_java.item15;

/**
 * Created by PC on 2016/4/24.
 */
public final class Complex {

    private double re;

    private double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    public Complex add(Complex other) {
        return new Complex(re + other.re, im + other.im);
    }

    public Complex substract(Complex other) {
        return new Complex(re - other.re, im - other.im);
    }

    public Complex multiply(Complex other) {
        return new Complex(re * other.re - im * other.im, re * other.im + im * other.re);
    }

    public Complex divide(Complex other) {
        double temp = other.re * other.re + other.im * other.im;
        return new Complex((re * other.re + im * other.im) / temp, (im * other.re - re * other.im) / temp);
    }
}
