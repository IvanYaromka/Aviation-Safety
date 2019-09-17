CREATE TABLE airline (
  id         BIGSERIAL,
  name       VARCHAR,
  country_id BIGINT
);

CREATE TABLE airplane (
  id      BIGSERIAL,
  name    VARCHAR,
  number  VARCHAR,
  year    DATE,
  type_id BIGINT
);

CREATE TABLE airport (
  id         BIGSERIAL,
  name       VARCHAR,
  icao       VARCHAR,
  country_id BIGINT
);

CREATE TABLE country (
  id   BIGSERIAL,
  name VARCHAR
);

CREATE TABLE pilot (
  id         BIGSERIAL,
  name       VARCHAR,
  surname    VARCHAR,
  airline_id BIGINT
);

CREATE TABLE airtype (
  id             BIGSERIAL,
  name           VARCHAR,
  rool           DOUBLE,
  take_off_pitch DOUBLE,
  land_pitch     DOUBLE,
  g_force        DOUBLE
);

CREATE TABLE users (
  id         BIGSERIAL,
  username   VARCHAR,
  password   VARCHAR,
  name       VARCHAR,
  surname    VARCHAR,
  airline_id BIGINT,
  role       INTEGER,
  enable     BOOLEAN
);

CREATE TABLE flight (
  id                  BIGSERIAL,
  take_off_date       DATE,
  land_date           DATE,
  first_pilot_id      BIGINT,
  second_pilot_id     BIGINT,
  take_off_airport_id BIGINT,
  land_airport_id     BIGINT,
  rool                DOUBLE,
  take_off_pitch      DOUBLE,
  land_pitch          DOUBLE,
  g_force             DOUBLE
);
