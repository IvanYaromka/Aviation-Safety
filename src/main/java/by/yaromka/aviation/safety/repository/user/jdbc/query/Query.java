package by.yaromka.aviation.safety.repository.user.jdbc.query;

public final class Query {
    public static final String SELECT_BY_USERNAME = "SELECT * FROM public.users WHERE username=:username";
    public static final String SELECT_ALL = "SELECT * FROM public.users";
    public static final String SELECT_BY_ID = "SELECT * FROM public.users where id=:id";
    public static final String SELECT_FOR_PAGE = "SELECT * FROM public.users limit :limit offset :offset";
    public static final String SELECT_COUNT = "SELECT count(*) FROM public.users";
    public static final String INSERT = "INSERT INTO public.users (username, password, name, surname, airline_id, role, enable) VALUES (:username, :password, :name, :surname, :airline_id, :role, :enable)";
    public static final String UPDATE = "UPDATE public.users SET username=:username, password=:password, name=:name, surname=:surname, airline_id=:airline_id, role=:role, enable=:enable WHERE id=:id";
    public static final String DELETE = "DELETE FROM public.users WHERE id=:id";
}
