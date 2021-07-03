package com.doublekit.apibox.client.mock;

import com.doublekit.apibox.client.mock.mocker.BeanMocker;
import com.doublekit.apibox.client.mock.mocker.PrimitiveMocker;
import com.doublekit.apibox.client.mock.support.MockUtils;

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
