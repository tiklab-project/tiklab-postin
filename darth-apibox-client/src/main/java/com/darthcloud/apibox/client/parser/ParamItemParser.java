package com.darthcloud.apibox.client.parser;

import com.darthcloud.apibox.client.mock.support.MockUtils;
import com.darthcloud.apibox.client.model.ApiPropertyMeta;
import com.darthcloud.apibox.client.model.ParamItem;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ParamItemParser {

    protected int deep = 0;

    /**
     * 解析子节点列表
     * @param paramItem
     */
    protected void setChildren(ParamItem paramItem){
        deep++;
        if(deep > 10){
            return;
        }
        Type type = null;
        Type paramType = null;
        if(paramItem.getType() instanceof ParameterizedType && paramItem.getParamType() == null){
            ParameterizedType parameterizedType = (ParameterizedType) paramItem.getType();
            type = parameterizedType.getRawType();
            for (Type actualType : parameterizedType.getActualTypeArguments()) {
                paramType = actualType;
            }
        }else{
            type = paramItem.getType();
            paramType = paramItem.getParamType();
        }

        if(MockUtils.isPrimitive(type)){
            return;
        }else if(MockUtils.isList(type)){
            if(paramType == null){
                return;
            }else{
                List<ApiPropertyMeta> apiPropertyMetaList = new ApiModelParser().parsePropertyMetas(paramType,null);
                if(apiPropertyMetaList != null && apiPropertyMetaList.size() > 0){
                    paramItem.setChildren(apiPropertyMetaList);

                    for(ApiPropertyMeta apiPropertyMeta:apiPropertyMetaList){
                        setChildren(apiPropertyMeta);
                    }
                }
            }
        }else{
            List<ApiPropertyMeta> apiPropertyMetaList = new ApiModelParser().parsePropertyMetas(type, paramType);
            if(apiPropertyMetaList != null && apiPropertyMetaList.size() > 0){
                paramItem.setChildren(apiPropertyMetaList);

                for(ApiPropertyMeta apiPropertyMeta:apiPropertyMetaList){
                    setChildren(apiPropertyMeta);
                }
            }
        }
    }

}
