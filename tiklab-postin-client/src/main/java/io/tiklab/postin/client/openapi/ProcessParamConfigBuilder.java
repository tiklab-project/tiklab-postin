package io.tiklab.postin.client.openapi;

import java.util.HashMap;

public class ProcessParamConfigBuilder {

    private static ProcessParamConfigBuilder processParamConfigBuilder = new ProcessParamConfigBuilder();

    public static ProcessParamConfig processParamConfig = new ProcessParamConfig();

    public static ProcessParamConfigBuilder instance(){
        return processParamConfigBuilder;
    }

    public ProcessParamConfigBuilder prePath(String preUrl){
        processParamConfig.setPrePath(preUrl);
        return processParamConfigBuilder;
    }

    public ProcessParamConfigBuilder setHeaders(HashMap<String,String> headers){
        processParamConfig.setHeaders(headers);
        return processParamConfigBuilder;
    }

    public ProcessParamConfig get(){
        return processParamConfig;
    }

}
