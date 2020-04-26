package org.songdan.tij.codewars.sku_3;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void simpleLiteral() {
        assertEquals("simple literal", new Double(127), Calculator.evaluate("127"), 1e-4);
    }

    @Test
    public void subtractionAndAddition() {
        assertEquals("addition", new Double(5), Calculator.evaluate("2 + 3"), 1e-4);
        assertEquals("subtraction", new Double(-5), Calculator.evaluate("2 - 3 - 4"), 1e-4);
    }

    @Test
    public void divisionAndMultiplication() {
        assertEquals("mixed division and multiplication", new Double(10), Calculator.evaluate("10 * 5 / 5"), 1e-4);
    }

    @Test
    public void allMixed() {
        assertEquals("mixed", new Double(13), Calculator.evaluate("2 / 2 + 3 * 4"), 1e-4);
    }

    @Test
    public void floats() {
        assertEquals("floats 1", new Double(0), Calculator.evaluate("7.7 - 3.3 - 4.4"), 1e-4);
    }

    @Test
    public void emptyString() {
        assertEquals("floats 1", new Double(-1), Calculator.evaluate(""), 1e-4);
    }

    @Test
    public void nevigate() {
        assertEquals("floats 1", new Double(-1), Calculator.evaluate("-1"), 1e-4);
    }

    @Test
    public void unknow() {
        assertEquals("floats 1", new Double(1), Calculator.evaluate("2+-1"), 1e-4);
    }

    @Test
    public void backet() {
        assertEquals("floats 1", new Double(15), Calculator.evaluate("(2+3)*(1+2)"), 1e-4);
    }

    @Test
    public void backetRecusive() {
        assertEquals("floats 1", new Double(0), Calculator.evaluate("(2+3)*((2*-2)+4)"), 1e-4);
    }

    @Test
    public void backetRecusiveV2() {
        assertEquals("floats 1", new Double(40), Calculator.evaluate("(2+3)*(-1*(2*-2)+4)"), 1e-4);
    }

}