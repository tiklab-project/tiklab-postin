package io.tiklab.postin.doclet.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.tiklab.postin.doclet.starter.DocletApplication;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class DocletGetModel {


    public static JSONObject loopModel(String modelFullName){
        JSONObject jsonObject = new JSONObject();
        try {

            //通过反射获取模型类
            Class<?> paramClass = Class.forName(modelFullName, true, DocletApplication.urlClassLoader);

            //获取模型类中的所有字段
            Field[] fields = paramClass.getDeclaredFields();
            // 遍历字段并获取字段信息
            for (Field field : fields) {

                // 非static字段
                if( field.getModifiers() == (Modifier.PRIVATE | Modifier.STATIC | Modifier.FINAL)) {continue;}

                // 获取字段名
                String fieldName = field.getName();
                // 获取字段类型
                Class<?> fieldType = field.getType();

                if (fieldType == int.class || fieldType.equals(Integer.class)) {
                    jsonObject.put(fieldName,0);
                } else if (fieldType == double.class || fieldType.equals(Double.class)) {
                    jsonObject.put(fieldName,0.0);
                } else if (fieldType == float.class || fieldType.equals(Float.class)) {
                    jsonObject.put(fieldName,0.0f);
                } else if (fieldType == long.class || fieldType.equals(Long.class)) {
                    jsonObject.put(fieldName,0L);
                } else if (fieldType == char.class || fieldType.equals(Character.class)) {
                    jsonObject.put(fieldName,'\u0000');
                } else if (fieldType == boolean.class || fieldType.equals(Boolean.class)) {
                    jsonObject.put(fieldName,false);
                } else if (fieldType == byte.class){
//                    System.out.println("Error --- :");
                } else if (fieldType == Enum.class || fieldType.isEnum()){
                    jsonObject.put(fieldName,fieldName);
                } else if (fieldType == String.class){
                    jsonObject.put(fieldName,fieldName);
                } else if (fieldType == List.class){
                    JSONArray jsonArray = new JSONArray();
                    // 获取 List 的泛型类型
                    Class<?> listGenericType = getListGenericType(field);
                    if (listGenericType != null) {
                        JSONObject elementJson = loopModel(listGenericType.getName());
                        jsonArray.add(elementJson);
                    }

                    jsonObject.put(fieldName, jsonArray);

                }else {
                    JSONObject modelJson = loopModel(fieldType.getName());
                    jsonObject.put(fieldName,modelJson);
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Error --- 获取Class失败 :"+e);
        }
        return jsonObject;
    }


    // 获取 List 的泛型类型
    private static Class<?> getListGenericType(Field field) {
        java.lang.reflect.Type genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            java.lang.reflect.Type[] typeArguments = parameterizedType.getActualTypeArguments();
            if (typeArguments.length > 0 && typeArguments[0] instanceof Class) {
                return (Class<?>) typeArguments[0];
            }
        }
        return null;
    }


}
