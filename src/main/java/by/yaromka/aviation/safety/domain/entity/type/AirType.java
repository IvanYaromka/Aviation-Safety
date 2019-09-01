package by.yaromka.aviation.safety.domain.entity.type;

import lombok.Data;

@Data
public class AirType {
    private Long id;
    private String name;
    private Double rool;
    private Double takeOffPitch;
    private Double landPitch;
    private Double gforce;
}
