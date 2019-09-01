package by.yaromka.aviation.safety.repository.airplane.jdbc.query;

public final class Query {
    public static final String SELECT_ALL = "SELECT * FROM airplane";
    public static final String SELECT_BY_ID = "SELECT * FROM airplane where id=:id";
    public static final String SELECT_FOR_PAGE = "SELECT * FROM airplane limit :limit offset :offset";
    public static final String SELECT_COUNT = "SELECT count(*) FROM airplane";
    public static final String INSERT = "INSERT INTO airplane (name, number, year, type_id) VALUES (:name, :number, :year, :type_id)";
    public static final String UPDATE = "UPDATE airplane SET name=:name, number=:number, year=:year, type_id=:type_id WHERE id=:id";
    public static final String DELETE = "DELETE FROM airplane WHERE id=:id";
}
