package by.yaromka.aviation.safety.domain.dto.pilot;

import lombok.Data;

@Data
public final class PilotResponse {
    private Long id;
    private String name;
    private String surname;
    private Long airlineId;
}
