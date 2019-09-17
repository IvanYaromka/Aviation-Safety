package by.yaromka.aviation.safety.domain.safety.response;

import lombok.Value;

import java.time.LocalDate;
import java.util.Map;

@Value
public class SafetyResponse {
    private Map<LocalDate, Double> roolExceedProbabilities; // Крен
    private Map<LocalDate, Double> takeOffPitchExceedProbabilities; // Тангаж на взлете
    private Map<LocalDate, Double> landPitchExceedProbabilities; // Тангаж на посадке
    private Map<LocalDate, Double> gforceExceedProbabilities; //Перегрузка
    private Map<LocalDate, Double> overallExceedProbabilities; // Вероятность что хоть одно из вышеперечисленный проявится

}
