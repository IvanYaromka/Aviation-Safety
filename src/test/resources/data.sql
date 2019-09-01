INSERT INTO airline (id, name, country_id) VALUES
(1, 'First', 1),
(2, 'Second', 2);

INSERT INTO airplane (id, name, number, year, type_id) VALUES
(1, 'First', '1', '0001-01-01', 1),
(2, 'Second', '2', '0002-02-02', 2);

INSERT INTO airport (id, name, icao, country_id) VALUES
(1, 'First', '1', 1),
(2, 'Second', '2', 2);

INSERT INTO country (id, name) VALUES
(1, 'First'),
(2, 'Second');

INSERT INTO pilot (id, name, surname, airline_id) VALUES
(1, 'First', 'First', 1),
(2, 'Second', 'Second', 2);

INSERT INTO airtype (id, name, rool, take_off_pitch, land_pitch, g_force) VALUES
(1, 'First', 1, 1, 1, 1),
(2, 'Second', 2, 2, 2, 2);

INSERT INTO user (id, username, password, name, surname, airline_id, role, enabled) VALUES
(1, 'First', 'First', 'First', 'First', '1', 1, 1, true),
(2, 'Second', 'Second', 'Second', 'Second', '2', 2, 2, true);

INSERT INTO flight (id, take_off_date, land_date, first_pilot_id, second_pilot_id, take_off_airport_id, land_airport_id, rool, take_off_pitch, land_pitch, g_force) VALUES
(1, '0001-01-01', '0001-01-01', 1, 2, 1, 2, 1, 1, 1, 1),
(2, '0002-02-02', '0002-02-02', 1, 2, 1, 2, 2, 2, 2, 2);
