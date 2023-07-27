package io.tiklab.postin.support.docletreport.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.category.model.Category;

import java.util.List;

@ApiModel
public class ModuleReport {

    private Category category;

    private List<ApiReport> moduleMethodList;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ApiReport> getModuleMethodList() {
        return moduleMethodList;
    }

    public void setModuleMethodList(List<ApiReport> moduleMethodList) {
        this.moduleMethodList = moduleMethodList;
    }
}
