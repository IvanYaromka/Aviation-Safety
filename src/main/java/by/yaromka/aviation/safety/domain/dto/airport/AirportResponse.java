package by.yaromka.aviation.safety.domain.dto.airport;

import lombok.Data;

@Data
public final class AirportResponse {
    private Long id;
    private String name;
    private String icao;
    private Long countryId;
}
