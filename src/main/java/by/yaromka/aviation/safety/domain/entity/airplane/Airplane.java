package by.yaromka.aviation.safety.domain.entity.airplane;

import by.yaromka.aviation.safety.domain.entity.type.AirType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Airplane {
    private Long id;
    private String name;
    private String number;
    private LocalDate year;
    private AirType type;
}
