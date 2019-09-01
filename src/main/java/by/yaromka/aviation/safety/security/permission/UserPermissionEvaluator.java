package by.yaromka.aviation.safety.security.permission;

import by.yaromka.aviation.safety.domain.entity.flight.Flight;
import by.yaromka.aviation.safety.domain.entity.pilot.Pilot;
import by.yaromka.aviation.safety.domain.entity.user.User;
import by.yaromka.aviation.safety.domain.entity.user.role.Role;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

@Component
public class UserPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object target, Object permission) {
        Object principal = authentication.getPrincipal();
        if (principal.getClass() == String.class) {
            return false;
        }

        User auth = (User) principal;
        if (auth.getRole() == Role.SUPERADMIN) {
            return true;
        }

        Class targetClass = target.getClass();
        if (targetClass == User.class) {
            return hasPermissionToUser(auth, (User) target);
        } else if (targetClass == Pilot.class) {
            return hasPermissionToPilot(auth, (Pilot) target);
        } else if (targetClass == Flight.class) {
            return hasPermissionToFlight(auth, (Flight) target);
        }

        return false;
    }

    private boolean hasPermissionToUser(User auth, User target) {
        if (auth.getRole() == Role.ADMIN) {
            return Objects.equals(auth.getAirlineId(), target.getAirlineId());
        }
        return Objects.equals(auth.getId(), target.getId());
    }

    private boolean hasPermissionToPilot(User auth, Pilot target) {
        return auth.getRole() == Role.ADMIN && Objects.equals(auth.getAirlineId(), target.getAirline().getId());
    }

    private boolean hasPermissionToFlight(User auth, Flight target) {
        return (auth.getRole() == Role.ADMIN || auth.getRole() == Role.SUPPLIER)
                && Objects.equals(auth.getAirlineId(), target.getFirstPilot().getAirline().getId());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        Object principal = authentication.getPrincipal();
        if (principal.getClass() == String.class) {
            return false;
        }

        User auth = (User) principal;
        if (auth.getRole() == Role.SUPERADMIN) {
            return true;
        }

        if (targetType.equals("User")) {
            return hasPermissionToUser(auth, (Long) targetId);
        } else if (targetType.equals("Pilot")) {
            return hasPermissionToPilot(auth, (Long) targetId);
        } else if (targetType.equals("Flight")) {
            return hasPermissionToFlight(auth, (Long) targetId);
        }

        return false;
    }

    private boolean hasPermissionToUser(User auth, Long targetId) {
        if (auth.getRole() == Role.ADMIN) {
            return Objects.equals(auth.getAirlineId(), targetId);
        }
        return Objects.equals(auth.getId(), targetId);
    }

    private boolean hasPermissionToPilot(User auth, Long targetId) {
        return auth.getRole() == Role.ADMIN && Objects.equals(auth.getAirlineId(), targetId);
    }

    private boolean hasPermissionToFlight(User auth, Long targetId) {
        return (auth.getRole() == Role.ADMIN || auth.getRole() == Role.SUPPLIER)
                && Objects.equals(auth.getAirlineId(), targetId);
    }
}
