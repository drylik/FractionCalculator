package ru.croc.calc;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


/**
 * Класс, выполняющий расчеты
 */
public class Calculator {

    /**
     * Поле path - путь к файлу с выражениями
     */
    private Path path;

    public Calculator() {
        path = null;
    }

    public Calculator(String pathToFile) {
        path = Paths.get(pathToFile);
    }

    /**
     * Метод, выполняющий расчеты
     * Если поле path равно null, то происходит работа в интерактивном режиме:
     * пользователь вводит выражения, результаты выводятся в консоль
     * так до тех пор, пока пользователь не введёт слово exit.
     * Иначе происходит работа с файлом:
     * считывание из файла, расчеты, запись результатов в другой файл и вывод их в консоль.
     */
    public void performCalculations() {
        //работа в интерактивном режиме
        if (path == null) {
            Scanner sc = new Scanner(System.in);
            String expression;
            while(true) {
                System.out.println("Для выхода введите exit\nВведите выражение:");
                expression = sc.nextLine();
                if (expression.equals("exit")) {
                    return;
                }
                expression = parseStr(expression);
                if (expression == null) {
                    System.err.println("Неверное выражение.");
                    continue;
                }
                System.out.println("= " + calculate(expression));
            }
            //работа с файлом
        } else {
            try {
                Parser parser = new JaxbParser();
                File file = new File("results.xml");
                ResultFractions resultFractions = new ResultFractions();
                List<String> expressions;
                expressions = Files.readAllLines(path, StandardCharsets.UTF_8);
                Iterator<String> iter = expressions.iterator();
                String expression;
                while (iter.hasNext()) {
                    expression = iter.next();
                    expression = parseStr(expression);
                    if (expression == null) {
                        resultFractions.add(null);
                        continue;
                    }
                    resultFractions.add(calculate(expression));
                }
                parser.saveObject(file, resultFractions);
            } catch (IOException e) {
                System.err.println("Ошибка во время чтения из файла.");
            } catch (JAXBException e) {
                System.err.println("Ошибка во время записи в файл.");
            }
        }
    }

    //проверка на правильность выражения
    public static boolean isExprCorrect(String expression) {
        String regex = "[0-9]*/[1-9]*[-+*/][0-9]*/[1-9]*";
        if (!expression.matches(regex)) {
            return false;
        }
        return true;
    }

    public static String parseStr(String expression) {
        expression = expression.replaceAll(" ", "");
        return isExprCorrect(expression) ? expression : null;
    }

    //выполнение математических действий
    public static Fraction calculate(String expression) {
        Fraction[] fractions = new Fraction[2];
        String arithmAction;
        //если деление , то символов '/' в строке 3
        if (expression.chars().filter(Integer.valueOf('/')::equals).count() == 3) {
            arithmAction = "/";
            String[] fracts = expression.split(arithmAction);
            fractions[0] = new Fraction(fracts[0] + "/" + fracts[1]);
            fractions[1] = new Fraction(fracts[2] + "/" + fracts[3]);
        } else {
            //в остальных случаях по одному символу на арифметическое действие
            if (expression.contains("+")) {
                arithmAction = "\\+";
            } else if (expression.contains("-")) {
                arithmAction = "-";
            } else {
                arithmAction = "\\*";
            }
            String[] fracts = expression.split(arithmAction);
            fractions[0] = new Fraction(fracts[0]);
            fractions[1] = new Fraction(fracts[1]);
        }
        fractions[0].performArithmeticAction(fractions[1], arithmAction);
        return fractions[0];
    }
}
