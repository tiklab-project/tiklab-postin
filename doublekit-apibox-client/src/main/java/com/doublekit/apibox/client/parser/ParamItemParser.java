package com.doublekit.apibox.client.parser;

import com.doublekit.apibox.client.mock.support.MockUtils;
import com.doublekit.apibox.client.model.ApiPropertyMeta;
import com.doublekit.apibox.client.model.ParamItem;
import com.doublekit.apibox.client.model.ParamItemType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ParamItemParser {

    //protected int deep = 0;

    /**
     * 解析子节点列表
     * @param paramItem
     * @param deep
     */
    protected void loop(ParamItem paramItem, int paramItemType,int deep){
        if(paramItemType == ParamItemType.TYPE_INPUT){
            //请求参数只解析两层嵌套模型
            if(deep > 2){
                return;
            }
        }else if(paramItemType == ParamItemType.TYPE_OUPUT){
            //响应结果只解析三层嵌套模型，如分页查询结果集
            if(deep > 3){
                return;
            }
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

                    //深度+1
                    deep++;

                    for(ApiPropertyMeta apiPropertyMeta:apiPropertyMetaList){
                        loop(apiPropertyMeta, paramItemType,deep);
                    }
                }
            }
        }else{
            List<ApiPropertyMeta> apiPropertyMetaList = new ApiModelParser().parsePropertyMetas(type, paramType);
            if(apiPropertyMetaList != null && apiPropertyMetaList.size() > 0){
                paramItem.setChildren(apiPropertyMetaList);

                //深度+1
                deep++;

                for(ApiPropertyMeta apiPropertyMeta:apiPropertyMetaList){
                    loop(apiPropertyMeta, paramItemType,deep);
                }
            }
        }
    }

}
