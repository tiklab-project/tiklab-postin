CREATE TABLE apibox_request_header_mock(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        header_name VARCHAR(64) NOT NULL,
        value VARCHAR(256),
        sort int
);