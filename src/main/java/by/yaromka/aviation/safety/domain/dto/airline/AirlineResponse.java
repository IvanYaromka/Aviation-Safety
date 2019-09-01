package by.yaromka.aviation.safety.domain.dto.airline;

import lombok.Data;

@Data
public final class AirlineResponse {
    private Long id;
    private String name;
    private Long countryId;
}
