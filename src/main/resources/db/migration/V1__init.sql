CREATE SEQUENCE PERSON_ID_SEQ;

CREATE TABLE PERSON (
    person_id BIGINT NOT NULL DEFAULT nextval('PERSON_ID_SEQ'),
    created_date timestamp DEFAULT CURRENT_TIMESTAMP,
    deleted boolean,
    last_modified_date timestamp DEFAULT NULL,
    version integer,
    first_name varchar(150) NOT NULL,
    last_name varchar(150) NOT NULL,
    age varchar(3) NOT NULL,
    favourite_colour VARCHAR(20) NOT NULL,
    PRIMARY KEY(person_id)
);

CREATE INDEX ix_fname ON PERSON(first_name);
CREATE INDEX ix_lname ON PERSON(last_name);
CREATE INDEX ix_fav_colour ON PERSON(favourite_colour);