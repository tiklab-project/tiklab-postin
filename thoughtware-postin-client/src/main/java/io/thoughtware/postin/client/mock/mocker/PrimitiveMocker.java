package io.thoughtware.postin.client.mock.mocker;

import java.lang.reflect.Type;

public class PrimitiveMocker {

    public static Object mock(Type type, String egValue){
        if(egValue == null || "".equals(egValue)){
            return null;
        }

        if(type == java.lang.String.class){
            return StringMocker.mock(egValue);
        }else if(type == int.class || type == java.lang.Integer.class){
            return IntegerMocker.mock(egValue);
        }else if(type == java.lang.Void.class){
            return null;
        }
        return null;
    }
}
