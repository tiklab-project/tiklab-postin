package io.tiklab.postin.client.mock.mocker;

import io.tiklab.postin.client.mock.support.MockUtils;

public class IntegerMocker {

    public static Integer mock(String egValue){
        if(!MockUtils.isMockExpress(egValue)){
            return Integer.valueOf(egValue);
        }else{
            egValue = egValue.replaceAll("@","");
            //不同表达式解析
            if(egValue.equals("###")){
                return null;
            }else{
                return null;
            }
        }
    }
}
