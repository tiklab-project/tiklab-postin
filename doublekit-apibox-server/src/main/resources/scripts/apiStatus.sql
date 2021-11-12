CREATE TABLE apibox_method_status(
        id VARCHAR(32) PRIMARY KEY,
        code VARCHAR(32),
        name VARCHAR(64),
        type VARCHAR(32)
);
INSERT INTO apibox_method_status values    ("designid","design","设计中","system"),
                                            ("developmentid","development","开发中","system"),
                                            ("testid","test","测试","system"),
                                            ("completeid","complete","完成","system"),
                                            ("maintainid","maintain","维护","system");