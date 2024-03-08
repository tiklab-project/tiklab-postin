package io.thoughtware.postin.category.entity;


import io.thoughtware.dal.jpa.annotation.Column;
import io.thoughtware.dal.jpa.annotation.Id;
import io.thoughtware.dal.jpa.annotation.Table;import io.thoughtware.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * 分类 实体
 */
@Entity
@Table(name="postin_category")
public class CategoryEntity implements Serializable {

    @Id
//     @GeneratorValue(length = 12)
    @Column(name = "id",length = 40)
    private String id;

//    //分类名称
//    @Column(name = "name",length = 64)
//    private String name;
//
//    //空间id
//    @Column(name = "workspace_id",length = 32)
//    private String workspaceId;
//
//    //所属上级
//    @Column(name = "parent_category_id",length = 32)
//    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
