package io.tiklab.postin.review.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.postin.api.apix.model.Apix;
import io.tiklab.postin.node.model.Node;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;

import java.sql.Timestamp;

/**
 *评审关联的用例的模型
 */
@ApiModel
@Mapper
@Join
public class ReviewApis extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;


    @ApiProperty(name="review",desc="所属评审")
    @Mappings({
            @Mapping(source = "review.id",target = "reviewId")
    })
    @JoinField(key = "id")
    private Review review;

    @ApiProperty(name="apiId",desc="接口id")
    @Mappings({
            @Mapping(source = "apix.id",target = "apiId")
    })
    @JoinField(key = "id")
    private Apix apix;

    @ApiProperty(name="apiId",desc="接口id")
    @Mappings({
            @Mapping(source = "node.id",target = "apiId")
    })
    @JoinField(key = "id")
    private Node node;

    @ApiProperty(name="result",desc="评审结果 1 通过   2未通过  3建议")
    private Integer result;

    @ApiProperty(name="comment",desc="评审意见")
    private String comment;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Apix getApix() {
        return apix;
    }

    public void setApix(Apix apix) {
        this.apix = apix;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
