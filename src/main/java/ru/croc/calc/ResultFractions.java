package ru.croc.calc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, хранящий результаты для вывода в файл
 */
@XmlRootElement
@XmlType(propOrder = {"results"})
public class ResultFractions {

    @XmlElement(name = "result")
    @XmlElementWrapper
    private List<String> results;
    public List<String> getResults() {
        return results;
    }

    public ResultFractions() {
        results = new ArrayList<>();
    }

    public void add(Fraction fraction) {
        results.add((fraction == null) ? "Неверное выражение." : fraction.toString());
    }
}
