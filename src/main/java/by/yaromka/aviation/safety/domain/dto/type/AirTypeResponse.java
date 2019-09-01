package by.yaromka.aviation.safety.domain.dto.type;

import lombok.Data;

@Data
public final class AirTypeResponse {
    private Long id;
    private String name;
    private Double rool;
    private Double takeOffPitch;
    private Double landPitch;
    private Double gforce;
}
