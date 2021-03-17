CREATE TABLE apibox_json_response(
        id VARCHAR(32) PRIMARY KEY,
        method_id VARCHAR(32) NOT NULL,
        property_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        required int NOT NULL,
        description VARCHAR(128),
        eg VARCHAR(128),
        sort int,
        parent_id VARCHAR(32)
);