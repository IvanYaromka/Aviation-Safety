package by.yaromka.aviation.safety.domain.dto.airplane;

import lombok.Data;

@Data
public final class AirplaneResponse {
    private Long id;
    private String name;
    private String number;
    private String date;
    private Long typeId;
}
