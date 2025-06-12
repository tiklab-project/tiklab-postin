package io.tiklab.postin.client.openapi;

import java.util.HashMap;

public class ParamConfigBuilder {

    private static ParamConfigBuilder paramConfigBuilder = new ParamConfigBuilder();

    public static ParamConfig paramConfig = new ParamConfig();

    public static ParamConfigBuilder instance(){
        return paramConfigBuilder;
    }

    public ParamConfigBuilder prePath(String preUrl){
        paramConfig.setPrePath(preUrl);
        return paramConfigBuilder;
    }

    public ParamConfigBuilder setHeaders(HashMap<String,String> headers){
        paramConfig.setHeaders(headers);
        return paramConfigBuilder;
    }

    public ParamConfigBuilder setScanPackage(String scanPackage){
        paramConfig.setScanPackage(scanPackage);
        return paramConfigBuilder;
    }

    public ParamConfig get(){
        return paramConfig;
    }

}
