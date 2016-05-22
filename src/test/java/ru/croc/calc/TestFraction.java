package ru.croc.calc;

import org.junit.Assert;
import org.junit.Test;

public class TestFraction {

    @Test
    public void testLCM() {
        Assert.assertEquals(6, Fraction.findLCM(2, 3));
        Assert.assertEquals(36, Fraction.findLCM(18, 12));
    }

    @Test
    public void additionTest() {
        Fraction[] fraction = { new Fraction("1/2"), new Fraction("3/4") };
        fraction[0].add(fraction[1]);
        Assert.assertEquals("5/4", fraction[0].toString());

        fraction[0] = new Fraction("2/3");
        fraction[1] = new Fraction("16/21");
        fraction[0].add(fraction[1]);
        Assert.assertEquals("30/21", fraction[0].toString());
    }

    @Test
    public void subtractionTest() {
        Fraction[] fraction = { new Fraction("1/2"), new Fraction("1/4") };
        fraction[0].subtract(fraction[1]);
        Assert.assertEquals("1/4", fraction[0].toString());

        fraction[0] = new Fraction("16/21");
        fraction[1] = new Fraction("2/3");
        fraction[0].subtract(fraction[1]);
        Assert.assertEquals("2/21", fraction[0].toString());

        fraction[0] = new Fraction("2/3");
        fraction[1] = new Fraction("16/21");
        fraction[0].subtract(fraction[1]);
        Assert.assertEquals("-2/21", fraction[0].toString());
    }

    @Test
    public void multiplicationTest() {
        Fraction[] fraction = { new Fraction("1/2"), new Fraction("3/4") };
        fraction[0].multiply(fraction[1]);
        Assert.assertEquals("3/8", fraction[0].toString());

        fraction[0] = new Fraction("2/3");
        fraction[1] = new Fraction("16/21");
        fraction[0].multiply(fraction[1]);
        Assert.assertEquals("32/63", fraction[0].toString());
    }

    @Test
    public void divisionTest() {
        Fraction[] fraction = { new Fraction("1/2"), new Fraction("3/4") };
        fraction[0].divide(fraction[1]);
        Assert.assertEquals("4/6", fraction[0].toString());

        fraction[0] = new Fraction("2/3");
        fraction[1] = new Fraction("16/21");
        fraction[0].divide(fraction[1]);
        Assert.assertEquals("42/48", fraction[0].toString());
    }
}
