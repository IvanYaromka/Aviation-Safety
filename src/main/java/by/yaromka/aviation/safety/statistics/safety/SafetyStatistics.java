package by.yaromka.aviation.safety.statistics.safety;

import by.yaromka.aviation.safety.statistics.approximator.Approximator;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.util.stream.Collectors.toMap;

@Component
public class SafetyStatistics {
    @Autowired
    private Approximator approximator;
    private static final double EPSILON = 0.05;

    public double calculate(List<Double> params, Double criticalN) {
        SortedMap<Double, Integer> groups = groupValuesWithEpsilon(params);
        SortedMap<Double, Double> groupsProbability = calculateNotExceedProbability(groups);
        SortedMap<Double, Double> groupsY = calculateY(groupsProbability);
        Pair<Double, Double> functionCoefficients = approximator.approximate(groupsY);
        double criticalY = calculateCriticalY(functionCoefficients.getLeft(), functionCoefficients.getRight(), criticalN);
        return calculateUnexceedProbability(criticalY);
    }

    private SortedMap<Double, Integer> groupValuesWithEpsilon(List<Double> params) {
        SortedMap<Double, Integer> result = new TreeMap<>();
        params.stream()
              .map(p -> Pair.of(p, Range.between(p - EPSILON, p + EPSILON)))
              .forEach(p -> putValueToGroup(p, result));
        return result;
    }

    private void putValueToGroup(Pair<Double, Range<Double>> pair, SortedMap<Double, Integer> groups) {
        Range<Double> range = pair.getRight();
        for (SortedMap.Entry<Double, Integer> e : groups.entrySet()) {
            if (range.contains((double) e.getValue())) {
                groups.compute(e.getKey(), (k, v) -> v + 1);
                return;
            }
        }
        groups.put(pair.getLeft(), 1);
    }

    private SortedMap<Double, Double> calculateNotExceedProbability(SortedMap<Double, Integer> grouped) {
        SortedMap<Double, Double> result = new TreeMap<>();
        int counter = 1;
        for (SortedMap.Entry<Double, Integer> g : grouped.entrySet()) {
            counter += g.getValue();
            result.put(g.getKey(), 1.0 / (double) counter);
        }
        return result;
    }

    private SortedMap<Double, Double> calculateY(SortedMap<Double, Double> groupsProbability) {
        return groupsProbability.entrySet()
                                .stream()
                                .peek(e -> e.setValue(-log(-log(e.getValue()))))
                                .sorted()
                                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue,
                                        (v1, v2) -> {
                                            throw new RuntimeException(String.format("Duplicate key for values %s and %s", v1, v2));
                                        },
                                        TreeMap::new));
    }

    private double calculateCriticalY(double criticalN, double k, double b) {
        return (criticalN - b) / k;
    }

    private double calculateUnexceedProbability(double criticalY) {
        return exp(exp(-criticalY));
    }
}
