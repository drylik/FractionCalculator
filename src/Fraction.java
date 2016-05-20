
public class Fraction {

    private int numerator;
    private int denominator;

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

    //нахождение НОК для ДВУХ чисел с сипользованием связи НОК и НОД
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

    //выполняет арифметическое действие в зависимости от строкового параметра
    public void performArythmeticAction(Fraction fraction, String arythmAction) {
        switch (arythmAction) {
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
