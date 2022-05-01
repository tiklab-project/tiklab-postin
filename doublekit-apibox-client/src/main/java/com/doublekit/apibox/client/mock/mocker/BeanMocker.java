package com.doublekit.apibox.client.mock.mocker;

import com.doublekit.apibox.client.model.ApiPropertyMeta;
import com.doublekit.apibox.client.parser.ApiModelParser;
import com.doublekit.utils.BeanUtils;
import com.doublekit.core.exception.ApplicationException;
import com.doublekit.apibox.annotation.ApiProperty;
import com.doublekit.apibox.client.mock.support.MockUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.util.List;

public class BeanMocker {

    public static Object mock(Type modelType, String egValue){
        //TODO 目前只处理内部属性mock
        Object instance = null;
        Type beanType = null;
        try {
            if (modelType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) modelType;
                Type rawType = parameterizedType.getRawType();
                beanType = modelType;
                modelType = rawType;
            }else{
                beanType = modelType;
            }

            instance = ((Class)modelType).newInstance();
            List<ApiPropertyMeta> apiPropertyMetaList = new ApiModelParser().parseProperties(beanType, null);
            for(ApiPropertyMeta apiPropertyMeta : apiPropertyMetaList){
                Type fieldType = apiPropertyMeta.getType();
                if(fieldType instanceof ParameterizedType){
                    /*
                    ParameterizedType parameterizedType = (ParameterizedType) fieldType;
                    Type rawType = parameterizedType.getRawType();
                    fieldType = rawType;
                     */
                    continue;
                }
                ApiProperty apiProperty = apiPropertyMeta.getApiProperty();
                String eg = apiProperty.eg();
                if(eg == null || "".equals(eg)){
                    continue;
                }

                Object value = null;
                if(MockUtils.isPrimitive(fieldType)){
                    value = PrimitiveMocker.mock(fieldType, eg);
                }else{
                    //内嵌套bean对象，如project.user
                    value = InnerBeanMocker.mock(fieldType, eg);
                }

                //设置eg属性值
                PropertyDescriptor pd = BeanUtils.getPropertyDescriptor((Class)modelType, apiProperty.name());
                Method writeMethod = pd.getWriteMethod();
                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                    writeMethod.setAccessible(true);
                }
                writeMethod.invoke(instance, value);
            }
        } catch (InstantiationException e) {
            throw new ApplicationException(e);
        } catch (IllegalAccessException e) {
            throw new ApplicationException(e);
        } catch (InvocationTargetException e) {
            throw new ApplicationException(e);
        }
        return instance;
    }

}
