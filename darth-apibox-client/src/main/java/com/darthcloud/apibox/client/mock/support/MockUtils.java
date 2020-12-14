package com.darthcloud.apibox.client.mock.support;

import java.lang.reflect.Type;

public class MockUtils {

    public static boolean isMockExpress(String egValue){
        if(egValue.startsWith("@")){
            return true;
        }
        return false;
    }

    public static boolean isPrimitive(Type type){
        if(type == int.class
                || type == java.lang.Void.class
                || type == java.lang.Integer.class
                || type == java.lang.String.class
                || type == java.lang.Boolean.class){
            return true;
        }
        return false;
    }
}
