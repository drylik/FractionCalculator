package ru.croc.calc;

public class Main {
    public static void main(String[] args) {
        Calculator calc;
        if (args.length == 0) {
            calc = new Calculator();
        } else {
            calc = new Calculator(args[0]);
        }
        calc.performCalculations();
    }
}
