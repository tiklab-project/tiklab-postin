CREATE TABLE apibox_form_param_mock(
        id VARCHAR(32) PRIMARY KEY,
        mock_id VARCHAR(32) NOT NULL,
        param_name VARCHAR(64) NOT NULL,
        value VARCHAR(256),
        sort int
);