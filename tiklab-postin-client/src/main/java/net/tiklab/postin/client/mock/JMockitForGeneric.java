package net.tiklab.postin.client.mock;

import net.tiklab.postin.client.mock.mocker.BeanMocker;
import net.tiklab.postin.client.mock.mocker.PrimitiveMocker;
import net.tiklab.postin.client.mock.support.MockUtils;

import java.lang.reflect.Type;

public class JMockitForGeneric {

    public static Object mock(Type type, String express){
        if(MockUtils.isPrimitive(type)){
            if(express == null){
                throw new IllegalArgumentException("primitive type express must not be null.");
            }
            Object egValue = PrimitiveMocker.mock(type,express);
            return egValue;
        }else{
            Object egValue = BeanMocker.mock(type,express);
            return egValue;
        }
    }
}
