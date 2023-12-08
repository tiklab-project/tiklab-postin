package io.thoughtware.postin.client.mock.support;

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
                || type == java.lang.String.class
                || type == java.lang.Integer.class
                || type == java.lang.Double.class
                || type == java.lang.Boolean.class
                || type == java.lang.Void.class){
            return true;
        }
        return false;
    }

    public static boolean isArray(Type type){
        if(type.getClass().isArray()){
            return true;
        }
        return false;
    }

    public static boolean isList(Type type){
        if(type == java.util.List.class){
            return true;
        }
        return false;
    }
}
