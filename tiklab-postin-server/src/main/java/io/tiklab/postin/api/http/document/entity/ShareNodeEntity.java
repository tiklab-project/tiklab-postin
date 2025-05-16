package io.tiklab.postin.api.http.document.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 分享绑定的节点 实体
 */
@Entity
@Table(name="postin_share_node")
public class ShareNodeEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 绑定的节点id
    @Column(name = "node_id")
    private String nodeId;

    // 所属分享
    @Column(name = "share_id")
    private String shareId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }


}
