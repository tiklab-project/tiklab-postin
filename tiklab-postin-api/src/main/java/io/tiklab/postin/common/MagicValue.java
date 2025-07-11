package io.tiklab.postin.common;

public class MagicValue {

    public static final String CATEGORY ="category";
    // protocol type
    public static final String PROTOCOL_TYPE_HTTP ="http";
    public static final String PROTOCOL_TYPE_WS ="ws";

    // api method
    public static final String API_METHOD_TYPE_GET = "get";
    public static final String API_METHOD_TYPE_POST = "post";
    public static final String API_METHOD_TYPE_PUT = "put";
    public static final String API_METHOD_TYPE_DELETE = "delete";
    public static final String API_METHOD_TYPE_PATCH = "patch";

    // request body type
    public static final String REQUEST_BODY_TYPE_NONE="none";
    public static final String REQUEST_BODY_TYPE_FORMDATA="formdata";
    public static final String REQUEST_BODY_TYPE_FORM_URLENCODED="formUrlencoded";
    public static final String REQUEST_BODY_TYPE_JSON="json";
    public static final String REQUEST_BODY_TYPE_RAW="raw";

    public static final String AUTHENTICATION_TYPE_NONE="none";
    public static final String AUTHENTICATION_TYPE_APIKEY="apikey";
    public static final String AUTHENTICATION_TYPE_BEARER="bearer";
    public static final String AUTHENTICATION_TYPE_BASIC="basic";
    public static final String AUTHENTICATION_TYPE_JWT="jwt";
    public static final String AUTHENTICATION_TYPE_DIGEST="digest";
    public static final String AUTHENTICATION_TYPE_NTLM="ntlm";
    public static final String AUTHENTICATION_TYPE_OAUTH1="oauth1";
    public static final String AUTHENTICATION_TYPE_OAUTH2="oauth2";

}
