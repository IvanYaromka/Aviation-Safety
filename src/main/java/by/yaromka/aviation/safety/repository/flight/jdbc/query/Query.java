package by.yaromka.aviation.safety.repository.flight.jdbc.query;

public final class Query {
    public static final String SELECT_LAST_FOR_PILOT = "SELECT * FROM flight WHERE first_pilot_id=:pilot_id OR second_pilot_id=:pilot_id ORDER BY take_off_date DESC LIMIT :limit";
    public static final String SELECT_BETWEEN_DATE_FOR_PILOT = "SELECT * FROM flight WHERE (first_pilot_id=:pilot_id OR second_pilot_id=:pilot_id) AND (take_off_date > :take_off_date AND land_date < :land_date)";
    public static final String SELECT_LAST_FOR_AIRPORT = "SELECT * FROM flight WHERE take_off_airport_id=:airport_id OR land_airport_id=:airport_id ORDER BY take_off_date DESC LIMIT :limit";
    public static final String SELECT_BETWEEN_DATE_FOR_AIRPORT = "SELECT * FROM flight WHERE (take_off_airport_id=:airport_id OR land_airport_id=:airport_id) AND (take_off_date > :take_off_date AND land_date < :land_date)";
    public static final String SELECT_LAST_FOR_AIRLINE = "SELECT f.*, p.airline_id as airline_id FROM flight f LEFT JOIN pilot p on p.id = f.first_pilot_id WHERE airline_id=:airline_id ORDER BY take_off_date DESC LIMIT :limit";
    public static final String SELECT_BETWEEN_DATE_FOR_AIRLINE = "SELECT f.*, p.airline_id as airline_id FROM flight f LEFT JOIN pilot p on p.id = f.first_pilot_id WHERE airline_id=:airline_id AND take_off_date > :take_off_date AND land_date < :land_date";

    public static final String SELECT_ALL = "SELECT * FROM flight";
    public static final String SELECT_BY_ID = "SELECT * FROM flight where id=:id";
    public static final String SELECT_FOR_PAGE = "SELECT * FROM flight limit :limit offset :offset";
    public static final String SELECT_COUNT = "SELECT count(*) FROM flight";
    public static final String INSERT = "INSERT INTO flight (take_off_date, land_date, first_pilot_id, second_pilot_id, take_off_airport_id, land_airport_id, rool, take_off_pitch, land_pitch, g_force) VALUES (:take_off_date, :land_date, :first_pilot_id, :second_pilot_id, :take_off_airport_id, :land_airport_id, :rool, :take_off_pitch, :land_pitch, :g_force)";
    public static final String UPDATE = "UPDATE flight SET take_off_date=:take_off_date, land_date=:land_date, first_pilot_id=:first_pilot_id, second_pilot_id=:second_pilot_id, take_off_airport_id=:take_off_airport_id, land_airport_id=:land_airport_id, rool=:rool, take_off_pitch=:take_off_pitch, land_pitch=:land_pitch, g_force=:g_force WHERE id=:id";
    public static final String DELETE = "DELETE FROM flight WHERE id=:id";
}
