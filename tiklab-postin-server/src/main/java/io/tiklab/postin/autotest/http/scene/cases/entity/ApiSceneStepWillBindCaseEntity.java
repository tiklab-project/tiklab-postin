package io.tiklab.postin.autotest.http.scene.cases.entity;


import io.tiklab.dal.jpa.annotation.Column;
import io.tiklab.dal.jpa.annotation.Entity;
import io.tiklab.dal.jpa.annotation.Id;

import java.io.Serializable;

/**
 *
 */
@Entity
public class ApiSceneStepWillBindCaseEntity implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "path",length = 32)
    private String path;

    @Column(name = "method_type",length = 32)
    private String methodType;

    @Column(name = "name",length = 32)
    private String name;

    @Column(name = "create_user",length = 32)
    private String createUser;

    @Column(name = "case_type",length = 32)
    private String caseType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }
}
