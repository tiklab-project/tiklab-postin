CREATE TABLE apibox_assert_testcase(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32) NOT NULL,
        property_name VARCHAR(64) NOT NULL,
        data_type VARCHAR(32) NOT NULL,
        comparator VARCHAR(32) NOT NULL,
        value VARCHAR(128),
        sort int
);