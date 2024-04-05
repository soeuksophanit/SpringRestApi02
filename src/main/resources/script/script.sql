CREATE DATABASE rest_api_002;

CREATE TABLE attendees(
                          attendees_id SERIAL PRIMARY KEY ,
                          attendees_name varchar(100),
                          email varchar(100)
);

CREATE TABLE venues(
                       venue_id SERIAL PRIMARY KEY ,
                       venue_name varchar(100),
                       location varchar(100)
);

CREATE TABLE events(
                       event_id SERIAL PRIMARY KEY ,
                       event_name varchar(100),
                       event_date Date,
                       venue_id INT,
                       FOREIGN KEY (venue_id) REFERENCES venues(venue_id)
);

CREATE TABLE event_attendee(
                               id SERIAL PRIMARY KEY ,
                               event_id INT,
                               FOREIGN KEY (event_id) REFERENCES events(event_id)
                                   ON DELETE CASCADE ON UPDATE CASCADE ,
                               attendee_id INT,
                               FOREIGN KEY (attendee_id) REFERENCES attendees(attendees_id)
                                   ON UPDATE CASCADE ON DELETE CASCADE
);


DELETE FROM venues WHERE venue_id = 6 RETURNING venue_id;

SELECT * FROM events
INNER JOIN event_attendee ea on events.event_id = ea.event_id;

