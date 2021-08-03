CREATE TABLE stru_subject(
        id VARCHAR(32) PRIMARY KEY,
        coding VARCHAR (64),
        name VARCHAR(64) NOT NULL,
        create_user varchar(30),
        create_time timestamp ,
        update_time timestamp

);
CREATE TABLE stru_json(
        id VARCHAR(32) PRIMARY KEY,
        subject_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        parent_id VARCHAR(32)
);
CREATE TABLE stru_enum(
        id VARCHAR(32) PRIMARY KEY,
        subject_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128)
);

