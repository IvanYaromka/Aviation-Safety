package by.yaromka.aviation.safety.domain.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public final class UserRequest {
    @NotNull
    @Pattern(regexp = "[A-Z][a-z]+")
    private String username;
    @NotNull
    @Pattern(regexp = "[A-Z][a-z]+")
    private String password;
    @NotNull
    @Pattern(regexp = "[A-Z][a-z]+")
    private String name;
    @NotNull
    @Pattern(regexp = "[A-Z][a-z]+")
    private String surname;

}
