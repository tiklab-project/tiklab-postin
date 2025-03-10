package io.tiklab.postin.client.parser;

import com.alibaba.fastjson.JSON;
import io.tiklab.postin.client.mock.JMockitForGeneric;
import io.tiklab.postin.client.model.TypeMetaEnum;
import io.tiklab.core.exception.SystemException;
import io.tiklab.postin.client.model.ApiResultMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ApiResultParser {

    private static Logger logger = LoggerFactory.getLogger(ApiResultParser.class);

    public ApiResultMeta parseResult(Method method){
        ApiResultMeta resultMeta = new ApiResultMeta();

        try {
            Type fieldType = null;
            Type paramType = null;
            Type genericReturnType = method.getGenericReturnType();
            if(genericReturnType instanceof ParameterizedType){
                ParameterizedType parameterizedType = (ParameterizedType) genericReturnType;
                fieldType = parameterizedType.getRawType();
                for (Type type : parameterizedType.getActualTypeArguments()) {
                    paramType = type;
                }
                String returnTypeText = parameterizedType.toString();
                returnTypeText = returnTypeText.replaceAll("<","&lt;");
                returnTypeText = returnTypeText.replaceAll(">","&gt;");

                resultMeta.setType(fieldType);
                resultMeta.setParamType(paramType);
                resultMeta.setReturnTypeText(returnTypeText);
            }else{
                fieldType = method.getReturnType();
                resultMeta.setType(fieldType);
                resultMeta.setReturnTypeText(fieldType.getTypeName());
            }

            //解析子节点列表
            new ApiTypeParser().parseChildren(resultMeta, TypeMetaEnum.TYPE_OUPUT,1);

            //deep = 0;

            if(resultMeta.getChildren() != null && resultMeta.getChildren().size() > 0){
                String textDef = JSON.toJSONString(resultMeta.getChildren(),true);
                resultMeta.setTextDef(textDef);
            }
        } catch (Throwable e) {
            String errorMsg = String.format("parse method result failed,method:%s.",method.getName());
            throw new SystemException(errorMsg,e);
        }

        /*
        Object def = getResultDef(genericReturnType);
        if(def != null){
            String textDef = JSON.toJSONString(def,true);
            resultMeta.setTextDef(textDef);
        }
        Object eg = getResultEgValue(genericReturnType);
        resultMeta.setEg(eg);
        if(eg != null){
            String textEg = JSON.toJSONString(eg,true);
            resultMeta.setTextEg(textEg);
        }
         */

        return resultMeta;
    }

    /**
     * 获取结果示例值
     * @param returnType
     * @return
     */
    Object getResultEgValue(Type returnType){
        return JMockitForGeneric.mock(returnType,null);
    }

    boolean isPrimitive(Class paramType){
        if(paramType.isPrimitive()
                || paramType == java.lang.Integer.class
                || paramType == java.lang.String.class
                || paramType == java.lang.Boolean.class){
            return true;
        }
        return false;
    }


}
