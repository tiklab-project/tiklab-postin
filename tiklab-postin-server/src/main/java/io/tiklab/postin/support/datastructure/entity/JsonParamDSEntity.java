package io.tiklab.postin.support.datastructure.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.GeneratorValue;
import io.tiklab.dal.jpa.annotation.Id;
import io.tiklab.dal.jpa.annotation.Table;import io.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * json 类型  实体
 */
@Entity @Table(name="postin_model_json")
public class JsonParamDSEntity implements Serializable {

    @Id
//     @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 所属数据结构
    @Column(name = "data_structure_id",length = 32)
    private String dataStructureId;

    @Column(name = "json_text",length = 2048)
    private String jsonText;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataStructureId() {
        return dataStructureId;
    }

    public void setDataStructureId(String dataStructureId) {
        this.dataStructureId = dataStructureId;
    }

    public String getJsonText() {
        return jsonText;
    }

    public void setJsonText(String jsonText) {
        this.jsonText = jsonText;
    }
}
