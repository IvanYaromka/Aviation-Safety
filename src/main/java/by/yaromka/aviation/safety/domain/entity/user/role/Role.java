package by.yaromka.aviation.safety.domain.entity.user.role;

public enum Role {
    SUPERADMIN, ADMIN, SUPPLIER, ANALYST;

    public static Role valueOf(Integer ordinal) {
        for (Role role : Role.values()) {
            if (role.ordinal() == ordinal) {
                return role;
            }
        }
        return null;
    }
}
