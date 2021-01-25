package com.darthcloud.apibox.client.model;

public class ParamItemType {

    public static int TYPE_INPUT = 1;
    public static int TYPE_OUPUT = 2;

    private int type = 1;//1:输入;2:输出

    public ParamItemType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
