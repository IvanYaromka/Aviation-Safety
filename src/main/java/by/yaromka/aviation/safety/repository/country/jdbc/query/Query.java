package by.yaromka.aviation.safety.repository.country.jdbc.query;

public final class Query {
    public static final String SELECT_ALL = "SELECT * FROM country";
    public static final String SELECT_BY_ID = "SELECT * FROM country where id=:id";
    public static final String SELECT_FOR_PAGE = "SELECT * FROM country limit :limit offset :offset";
    public static final String SELECT_COUNT = "SELECT count(*) FROM country";
    public static final String INSERT = "INSERT INTO country (name) VALUES (:name)";
    public static final String UPDATE = "UPDATE country SET name=:name WHERE id=:id";
    public static final String DELETE = "DELETE FROM country WHERE id=:id";
}
