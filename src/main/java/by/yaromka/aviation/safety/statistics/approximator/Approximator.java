package by.yaromka.aviation.safety.statistics.approximator;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Approximator {
    public Pair<Double, Double> approximate(Map<Double, Double> params) {
        int n = params.size();
        double sumX = 0;
        double sumY = 0;
        double sumX2 = 0;
        double sumXY = 0;

        for (Map.Entry<Double, Double> entry : params.entrySet()) {
            double x = entry.getKey();
            double y = entry.getValue();
            sumX += x;
            sumY += y;
            sumX2 += x * x;
            sumXY += x * y;
        }

        double k = (n * sumXY - (sumX * sumY)) / (n * sumX2 - sumX * sumX);
        double b = (sumX - k * sumX) / n;

        return Pair.of(k, b);
    }
}
