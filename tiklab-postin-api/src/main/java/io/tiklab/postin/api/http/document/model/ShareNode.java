package io.tiklab.postin.api.http.document.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.postin.node.model.Node;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.join.annotation.Join;

import javax.validation.constraints.NotNull;

/**
 * 分享绑定的节点 模型
 */
@ApiModel
@Mapper
@Join
public class ShareNode extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="nodeId",desc="绑定的节点id")
    private String nodeId;

    @ApiProperty(name="shareId",desc="所属分享 ID")
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
