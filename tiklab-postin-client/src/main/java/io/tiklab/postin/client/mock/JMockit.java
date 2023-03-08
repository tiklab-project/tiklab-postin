package io.tiklab.postin.client.mock;

import io.tiklab.postin.client.mock.support.MockUtils;
import io.tiklab.postin.client.mock.mocker.BeanMocker;
import io.tiklab.postin.client.mock.mocker.PrimitiveMocker;

public class JMockit {

    public static <T> T mock(Class<T> type){
        return doMock(type,null);
    }

    public static <T> T mock(Class<T> type,String express){
        return doMock(type,express);
    }

    public static <T> T doMock(Class<T> type, String express){
        if(MockUtils.isPrimitive(type)){
            if(express == null){
                throw new IllegalArgumentException("primitive type express must not be null.");
            }
            T egValue = (T) PrimitiveMocker.mock(type,express);
            return egValue;
        }else{
            T egValue = (T) BeanMocker.mock(type,express);
            return egValue;
        }
    }
}
