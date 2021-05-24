package com.darthcloud.apibox.client.mock.mocker;


import java.lang.reflect.Type;
import java.util.List;

public class InnerBeanMocker {

    //static JoinProvider joinProvider = new DefaultJoinProvider();

    /**
     * 构造内部bean，如project.user
     * @param paramType
     * @param egValue
     * @return
     */
    public static Object mock(Type paramType, String egValue){
        if(!egValue.startsWith("@")){
            return null;
        }
        egValue = egValue.replaceAll("@","");

        if(egValue.equals("selectOne")){
            return selectOne((Class) paramType);
        }else if(egValue.equals("new")){
            try {
                Object obj = ((Class)paramType).newInstance();
                return obj;
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }else{
            return null;
        }
    }

    static Object selectOne(Class<?> paramType){
        List<?> list = null;//TODO joinProvider.findAll(paramType);
        if(list == null || list.size()==0){
            return null;
        }

        Object record = list.get(0);
        return record;
    }
}
