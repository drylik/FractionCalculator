package ru.croc.calc.test;

import org.junit.Assert;
import org.junit.Test;
import ru.croc.calc.java.Calculator;
import ru.croc.calc.java.Fraction;

public class TestStringParsing {

    @Test
    public void parsingTest() {
        String[] expressions = {"1/3 + 4/5", "2 /8 / 6/ 5", "2/ 7 * 0 /7", "0 / 5 - 6/4", "5 / 0 - 6/4", "2/ 7 . 0 /7", "a/5 + 4/9"};
        for (int i = 0 ; i < expressions.length; i++) {
            expressions[i] = Calculator.parseStr(expressions[i]);
        }
        Assert.assertEquals("1/3+4/5", expressions[0]);
        Assert.assertEquals("2/8/6/5", expressions[1]);
        Assert.assertEquals("2/7*0/7", expressions[2]);
        Assert.assertEquals("0/5-6/4", expressions[3]);
        Assert.assertEquals(null, expressions[4]);
        Assert.assertEquals(null, expressions[5]);
        Assert.assertEquals(null, expressions[6]);
    }

    @Test
    public void parsingStrToFractionTest() {
        String expression = "5/4";
        Fraction fraction = new Fraction(expression);
        Assert.assertEquals(5, fraction.getNumerator());
        Assert.assertEquals(4, fraction.getDenominator());
        expression = "1/7";
        fraction = new Fraction(expression);
        Assert.assertEquals(1, fraction.getNumerator());
        Assert.assertEquals(7, fraction.getDenominator());
    }
}
