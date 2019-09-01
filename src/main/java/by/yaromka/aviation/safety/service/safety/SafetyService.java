package by.yaromka.aviation.safety.service.safety;

import by.yaromka.aviation.safety.domain.safety.response.SafetyResponse;

import java.time.LocalDate;

public interface SafetyService {
    SafetyResponse queryByDateRange(Long id, LocalDate begin, LocalDate end);
}
