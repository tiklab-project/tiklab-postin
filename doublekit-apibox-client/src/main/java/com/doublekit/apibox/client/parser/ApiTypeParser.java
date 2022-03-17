package com.doublekit.apibox.client.parser;

import com.doublekit.apibox.client.mock.support.MockUtils;
import com.doublekit.apibox.client.model.ApiPropertyMeta;
import com.doublekit.apibox.client.model.TypeMeta;
import com.doublekit.apibox.client.model.TypeMetaEnum;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 数据类型解析
 */
public class ApiTypeParser {

    //protected int deep = 0;

    /**
     * 解析子节点列表
     * @param typeMeta
     * @param deep
     */
    protected void parseChildren(TypeMeta typeMeta, int typeCategory, int deep){
        if(typeCategory == TypeMetaEnum.TYPE_INPUT){
            //请求参数只解析两层嵌套模型
            if(deep > 3){
                return;
            }
        }else if(typeCategory == TypeMetaEnum.TYPE_OUPUT){
            //响应结果只解析三层嵌套模型，如分页查询结果集
            if(deep > 3){
                return;
            }
        }

        Type type = null;
        Type paramType = null;
        if(typeMeta.getType() instanceof ParameterizedType && typeMeta.getParamType() == null){
            ParameterizedType parameterizedType = (ParameterizedType) typeMeta.getType();
            type = parameterizedType.getRawType();
            for (Type actualType : parameterizedType.getActualTypeArguments()) {
                paramType = actualType;
            }
        }else{
            type = typeMeta.getType();
            paramType = typeMeta.getParamType();
        }

        if(MockUtils.isPrimitive(type)){
            return;
        }else if(MockUtils.isList(type)){
            if(paramType == null){
                return;
            }else{
                List<ApiPropertyMeta> apiPropertyMetaList = new ApiModelParser().parseProperties(paramType,null);
                if(apiPropertyMetaList != null && apiPropertyMetaList.size() > 0){
                    typeMeta.setChildren(apiPropertyMetaList);

                    //深度+1
                    deep++;

                    for(ApiPropertyMeta apiPropertyMeta:apiPropertyMetaList){
                        parseChildren(apiPropertyMeta, typeCategory,deep);
                    }
                }
            }
        }else{
            List<ApiPropertyMeta> apiPropertyMetaList = new ApiModelParser().parseProperties(type, paramType);
            if(apiPropertyMetaList != null && apiPropertyMetaList.size() > 0){
                typeMeta.setChildren(apiPropertyMetaList);

                //深度+1
                deep++;

                for(ApiPropertyMeta apiPropertyMeta:apiPropertyMetaList){
                    parseChildren(apiPropertyMeta, typeCategory,deep);
                }
            }
        }
    }

}
