package by.yaromka.aviation.safety.repository.type.jdbc.query;

public final class Query {
    public static final String SELECT_ALL = "SELECT * FROM airtype";
    public static final String SELECT_BY_ID = "SELECT * FROM airtype where id=:id";
    public static final String SELECT_FOR_PAGE = "SELECT * FROM airtype limit :limit offset :offset";
    public static final String SELECT_COUNT = "SELECT count(*) FROM airtype";
    public static final String INSERT = "INSERT INTO airtype (name, rool, take_off_pitch, land_pitch, g_force) VALUES (:name, :rool, :take_off_pitch, :land_pitch, :g_force)";
    public static final String UPDATE = "UPDATE airtype SET name=:name, rool=:rool, take_off_pitch=:take_off_pitch, land_pitch=:land_pitch, g_force=:g_force WHERE id=:id";
    public static final String DELETE = "DELETE FROM airtype WHERE id=:id";
}
