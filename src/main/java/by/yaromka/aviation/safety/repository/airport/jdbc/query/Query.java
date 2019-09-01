package by.yaromka.aviation.safety.repository.airport.jdbc.query;

public final class Query {
    public static final String SELECT_ALL = "SELECT * FROM airport";
    public static final String SELECT_BY_ID = "SELECT * FROM airport where id=:id";
    public static final String SELECT_FOR_PAGE = "SELECT * FROM airport limit :limit offset :offset";
    public static final String SELECT_COUNT = "SELECT count(*) FROM airport";
    public static final String INSERT = "INSERT INTO airport (name, icao, country_id) VALUES (:name, :icao, :country_id)";
    public static final String UPDATE = "UPDATE airport SET name=:name, icao=:icao, country_id=:country_id WHERE id=:id";
    public static final String DELETE = "DELETE FROM airport WHERE id=:id";
}
