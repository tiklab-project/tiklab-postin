package net.tiklab.postin.client.builder;

import net.tiklab.postin.client.model.ApiMeta;

import java.util.ArrayList;
import java.util.List;

public class ApiData {

    List<ApiMeta> apis = new ArrayList<>();

    public List<ApiMeta> getApis() {
        return apis;
    }

    public void setApis(List<ApiMeta> apis) {
        this.apis = apis;
    }
}
