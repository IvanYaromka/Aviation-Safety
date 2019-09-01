package by.yaromka.aviation.safety.repository.airline.jdbc.query;

public final class Query {
    public static final String SELECT_ALL = "SELECT * FROM airline";
    public static final String SELECT_BY_ID = "SELECT * FROM airline where id=:id";
    public static final String SELECT_FOR_PAGE = "SELECT * FROM airline limit :limit offset :offset";
    public static final String SELECT_COUNT = "SELECT count(*) FROM airline";
    public static final String INSERT = "INSERT INTO airline (name, country_id) VALUES (:name, :country_id)";
    public static final String DELETE = "DELETE FROM airline WHERE id=:id";
    public static final String UPDATE = "UPDATE airline SET name=:name, country_id=:country_id WHERE id=:id";
}
