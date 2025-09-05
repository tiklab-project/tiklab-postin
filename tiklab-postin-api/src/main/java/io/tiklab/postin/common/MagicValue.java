package io.tiklab.postin.common;

public class MagicValue {

    public static final String CATEGORY ="category";
    // protocol type
    public static final String PROTOCOL_TYPE_HTTP ="http";
    public static final String PROTOCOL_TYPE_WS ="ws";

    // http method
    public static final String API_METHOD_TYPE_GET = "get";
    public static final String API_METHOD_TYPE_POST = "post";
    public static final String API_METHOD_TYPE_PUT = "put";
    public static final String API_METHOD_TYPE_DELETE = "delete";
    public static final String API_METHOD_TYPE_PATCH = "patch";
    public static final String API_METHOD_TYPE_OPTIONS = "options";
    public static final String API_METHOD_TYPE_HEAD = "head";

    // request body type
    public static final String REQUEST_BODY_TYPE_NONE="none";
    public static final String REQUEST_BODY_TYPE_FORMDATA="formdata";
    public static final String REQUEST_BODY_TYPE_FORM_URLENCODED="formUrlencoded";
    public static final String REQUEST_BODY_TYPE_JSON="json";
    public static final String REQUEST_BODY_TYPE_RAW="raw";

    // media type
    public static final String MEDIA_TYPE_JSON="application/json";
    public static final String MEDIA_TYPE_FORM_DATA="multipart/form-data";
    public static final String MEDIA_TYPE_FORM_URLENCODED="application/x-www-form-urlencoded";
    public static final String MEDIA_TYPE_RAW="text/plain";
    public static final String MEDIA_TYPE_XML="application/xml";
    public static final String MEDIA_TYPE_HTML="text/html";

    public static final String AUTHENTICATION_TYPE_NONE="none";
    public static final String AUTHENTICATION_TYPE_APIKEY="apikey";
    public static final String AUTHENTICATION_TYPE_BEARER="bearer";
    public static final String AUTHENTICATION_TYPE_BASIC="basic";
    public static final String AUTHENTICATION_TYPE_JWT="jwt";
    public static final String AUTHENTICATION_TYPE_DIGEST="digest";
    public static final String AUTHENTICATION_TYPE_NTLM="ntlm";
    public static final String AUTHENTICATION_TYPE_OAUTH1="oauth1";
    public static final String AUTHENTICATION_TYPE_OAUTH2="oauth2";

    // 前置后置 操作类型
    public static final String OPERATION_TYPE_DATABASE = "database";
    public static final String OPERATION_TYPE_SCRIPT = "script";


    // 数据库连接类型
    public static final String DATABASE_CONNECT_TYPE_POSTGRESQL = "postgresql";
    public static final String DATABASE_CONNECT_TYPE_MYSQL = "mysql";


    //步骤断言的类型：变量，元素
    public static final String ASSERT_TYPE_VARIABLE = "variable";

    //值断言的比较
    public static final int EQUAL = 1;
    public static final int NOT_EQUAL = 2;
    public static final int LESS_THAN = 3;
    public static final int LESS_THAN_OR_EQUAL = 4;
    public static final int GREATER_THAN = 5;
    public static final int GREATER_THAN_OR_EQUAL = 6;

    //元素断言 类型
    //期望值
    public static final int EXPECT = 1;
    //元素存在
    public static final int ELEMENT_EXIST = 2;
    //元素不存在
    public static final int ELEMENT_NOT_EXIST = 3;

    //用例类型
    public static final String CASE_TYPE_API_UNIT = "api-unit";
    public static final String CASE_TYPE_API_SCENE = "api-scene";
    public static final String CASE_TYPE_API_PERFORM = "api-perform";
    public static final String TEST_PLAN = "test-plan";

    //测试步骤类型
//    public static final String TEST_STEP_CASE= "case";
    public static final String TEST_STEP_IF = "if";
    public static final String TEST_STEP_SCRIPT = "script";
    public static final String TEST_STEP_DATABASE = "database";
    public static final String TEST_STEP_FOR = "for";


    public static final String TEST_API_PERFORM_STOP = "api-perform-stop";

    //测试类型
    //接口 单元/场景
    public static final String TEST_TYPE_API = "api";
    //功能
    public static final String TEST_TYPE_FUNCTION = "function";
    //ui  web/app
    public static final String TEST_TYPE_UI = "ui";
    //性能
    public static final String TEST_TYPE_PERFORM = "perform";


    //测试步骤中的if判断关系，并且
    public static final String IF_RELATION_AND = "and";
    //测试步骤中的if判断关系，或者
    public static final String IF_RELATION_OR = "or";

    //测试状态
    //开始执行
    public static final String TEST_STATUS_START = "start";
    //执行成功
    public static final String TEST_STATUS_SUCCESS= "success";
    //执行失败
    public static final String TEST_STATUS_FAIL = "fail";
    //执行完成
    public static final String TEST_STATUS_COMPLETE = "complete";

    //agent 默认id
    public static final String AGENT_DEFAULT = "agent-default_localhost";




}
