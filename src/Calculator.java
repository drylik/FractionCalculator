import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

@XmlRootElement
@XmlType(propOrder = {"results"})
public class Calculator {

    @XmlElement(name = "result")
    @XmlElementWrapper
    private List<String> results;
    public List<String> getResults() {
        return results;
    }

    public Calculator() {
        results = new ArrayList<>();
    }

    public void performCalculations(String[] args) {
        //работа в интерактивном режиме
        if (args.length == 0) {
            Scanner sc = new Scanner(System.in);
            String expression;
            while(true) {
                System.out.println("Для выхода введите exit\nВведите выражение:");
                expression = sc.nextLine();
                if (expression.equals("exit")) {
                    return;
                }
                expression = expression.replaceAll(" ", "");
                if (!isExprCorrect(expression)) {
                    System.err.println("Неверное выражение.");
                    continue;
                }
                System.out.println("= " + calculate(expression));
            }
            //работа с файлом
        } else {
            try {
                Parser parser = new JaxbParser();
                Path path = Paths.get(args[0]);
                File file = new File("results.xml");
                List<String> expressions;
                expressions = Files.readAllLines(path, StandardCharsets.UTF_8);
                Iterator<String> iter = expressions.iterator();
                String expression;
                while (iter.hasNext()) {
                    expression = iter.next();
                    expression = expression.replaceAll(" ", "");
                    if (!isExprCorrect(expression)) {
                        results.add("Неверное выражение.");
                        continue;
                    }
                    results.add(calculate(expression).toString());
                }
                parser.saveObject(file, this);
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

    //выполнение математических действий
    public static Fraction calculate(String expression) {
        Fraction[] fractions = new Fraction[2];
        String arythmAction;
        //если деление , то символов '/' в строке 3
        if (expression.chars().filter(Integer.valueOf('/')::equals).count() == 3) {
            arythmAction = "/";
            String[] fracts = expression.split(arythmAction);
            fractions[0] = new Fraction(fracts[0] + "/" + fracts[1]);
            fractions[1] = new Fraction(fracts[2] + "/" + fracts[3]);
        } else {
            //в остальных случаях по одному символу на арифметическое действие
            if (expression.contains("+")) {
                arythmAction = "\\+";
            } else if (expression.contains("-")) {
                arythmAction = "-";
            } else {
                arythmAction = "\\*";
            }
            String[] fracts = expression.split(arythmAction);
            fractions[0] = new Fraction(fracts[0]);
            fractions[1] = new Fraction(fracts[1]);
        }
        fractions[0].performArythmeticAction(fractions[1], arythmAction);
        return fractions[0];
    }
}
