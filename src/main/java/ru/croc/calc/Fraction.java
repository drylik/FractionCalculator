package ru.croc.calc;

/**
 * класс для дробей
 * имеет поля:
 * numerator - для числителя
 * denominator - для знаменателя
 * выполняет арифметические действия
 */
public class Fraction {

    private int numerator;
    private int denominator;

    /**
     * Конструктор
     * @param fraction - строка вида x/y
     * проверка на правильность входящего параметра происходит вне конструктора в другом классе
     */
    public Fraction(String fraction) {
        String[] numbs = fraction.split("/");
        numerator = Integer.parseInt(numbs[0]);
        denominator = Integer.parseInt(numbs[1]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fraction)) return false;

        Fraction fraction = (Fraction) o;

        if (numerator != fraction.numerator) return false;
        if (denominator != fraction.denominator) return false;

        return true;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    /**
     * нахождение НОК для ДВУХ чисел
     * @param number1 первое число
     * @param number2 второе число
     * @return НОК двух чисел с сипользованием связи НОК и НОД
     */
    public static int findLCM(int number1, int number2) {
        //НОД:
        int[] mcd = {number1, number2};
        while (mcd[0] != 0 && mcd[1] != 0) {
            if (mcd[0] > mcd[1]) {
                mcd[0] %= mcd[1];
            } else {
                mcd[1] %= mcd[0];
            }
        }
        //НОК:
        return (number1 * number2) / (mcd[0] + mcd[1]);
    }

    public void add(Fraction fraction) {
        //НОК знаменателей
        int lcm = findLCM(denominator, fraction.denominator);
        numerator *= lcm / denominator;
        numerator += lcm / fraction.denominator * fraction.numerator;
        denominator = lcm;
    }

    public void subtract(Fraction fraction) {
        //НОК знаменателей
        int lcm = findLCM(denominator, fraction.denominator);
        numerator *= lcm / denominator;
        numerator -= lcm / fraction.denominator * fraction.numerator;
        denominator = lcm;
    }

    public void multiply(Fraction fraction) {
        numerator *= fraction.numerator;
        denominator *= fraction.denominator;
    }

    public void divide(Fraction fraction) {
        numerator *= fraction.denominator;
        denominator *= fraction.numerator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    /**
     * выполняет арифметическое действие в зависимости от строкового параметра
     * @param fraction - операнд
     * @param arithmAction - арифметическое действие
     */
    public void performArithmeticAction(Fraction fraction, String arithmAction) {
        switch (arithmAction) {
            case "\\+":
                this.add(fraction);
                break;
            case "-":
                this.subtract(fraction);
                break;
            case "\\*":
                this.multiply(fraction);
                break;
            case "/":
                this.divide(fraction);
                break;
            default:
                break;
        }
    }
}
