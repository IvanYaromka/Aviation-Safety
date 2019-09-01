package by.yaromka.aviation.safety.repository.pilot.jdbc.query;

public final class Query {
    public static final String SELECT_ALL = "SELECT * FROM pilot";
    public static final String SELECT_BY_ID = "SELECT * FROM pilot where id=:id";
    public static final String SELECT_FOR_PAGE = "SELECT * FROM pilot limit :limit offset :offset";
    public static final String SELECT_COUNT = "SELECT count(*) FROM pilot";
    public static final String INSERT = "INSERT INTO pilot (name, surname, airline_id) VALUES (:name, :surname, :airline_id)";
    public static final String UPDATE = "UPDATE pilot SET name=:name, surname=:surname, airline_id=:airline_id WHERE id=:id";
    public static final String DELETE = "DELETE FROM pilot WHERE id=:id";
}
