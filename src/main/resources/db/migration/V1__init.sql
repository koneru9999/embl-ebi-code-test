CREATE TABLE COLOR (
  color_code VARCHAR(20) NOT NULL,
  description VARCHAR(150),
  PRIMARY KEY(color_code)
);

CREATE TABLE PERSON (
    person_id uuid DEFAULT uuid_generate_v4(),
    created_date timestamp DEFAULT CURRENT_TIMESTAMP,
    deleted boolean,
    last_modified_date timestamp DEFAULT NULL,
    version integer,
    first_name varchar(150),
    last_name varchar(50) UNIQUE NOT NULL,
    age integer NOT NULL,
    favourite_colour_id VARCHAR(20) REFERENCES COLOR(color_code),
    PRIMARY KEY(person_id)
);

CREATE INDEX ix_fname ON PERSON(first_name);
CREATE INDEX ix_lname ON PERSON(last_name);

CREATE TABLE PERSON_EXTENSIONS (
    person_id uuid REFERENCES PERSON(person_id) NOT NULL,
    extension_key varchar(100) NOT NULL,
    extension_value varchar(255),
    CONSTRAINT key_id PRIMARY KEY(extension_key, person_id)
);