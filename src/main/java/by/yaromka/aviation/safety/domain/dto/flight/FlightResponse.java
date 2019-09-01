package by.yaromka.aviation.safety.domain.dto.flight;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public final class FlightResponse {
    @NotNull
    private String takeOffDate;
    @NotNull
    private String landDate;
    @NotNull
    @Min(1)
    private Long firstPilotId;
    @NotNull
    @Min(1)
    private Long secondPilotId;
    @NotNull
    @Min(1)
    private Long takeOffAirportId;
    @NotNull
    @Min(1)
    private Long landAirportId;
    @NotNull
    private Double rool;
    @NotNull
    private Double takeOffPitch;
    @NotNull
    private Double landPitch;
    @NotNull
    private Double gforce;
}
