CREATE TABLE apibox_test_instance(
        id VARCHAR(32) PRIMARY KEY,
        testcase_id VARCHAR(32) NOT NULL,
        testNo int NOT NULL,
        statusCode int NOT NULL,
        result int NOT NULL
);