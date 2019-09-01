package by.yaromka.aviation.safety.repository.user.jdbc.query;

public final class Query {
    public static final String SELECT_BY_USERNAME = "SELECT * FROM public.user WHERE username=:username";
    public static final String SELECT_ALL = "SELECT * FROM public.user";
    public static final String SELECT_BY_ID = "SELECT * FROM public.user where id=:id";
    public static final String SELECT_FOR_PAGE = "SELECT * FROM public.user limit :limit offset :offset";
    public static final String SELECT_COUNT = "SELECT count(*) FROM public.user";
    public static final String INSERT = "INSERT INTO public.user (username, password, name, surname, airline_id, role, enabled) VALUES (:username, :password, :name, :surname, :airline_id, :role, :enabled)";
    public static final String UPDATE = "UPDATE public.user SET username=:username, password=:password, name=:name, surname=:surname, airline_id=:airline_id, role=:role, enabled=:enabled WHERE id=:id";
    public static final String DELETE = "DELETE FROM public.user WHERE id=:id";
}
