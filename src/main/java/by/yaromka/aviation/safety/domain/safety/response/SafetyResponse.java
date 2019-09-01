package by.yaromka.aviation.safety.domain.safety.response;

import lombok.Value;

import java.time.LocalDate;
import java.util.Map;

@Value
public class SafetyResponse {
    private Map<LocalDate, Double> roolExceedProbabilities;
    private Map<LocalDate, Double> takeOffPitchExceedProbabilities;
    private Map<LocalDate, Double> landPitchExceedProbabilities;
    private Map<LocalDate, Double> gforceExceedProbabilities;
    private Map<LocalDate, Double> overallExceedProbabilities;

}
