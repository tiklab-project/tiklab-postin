package io.tiklab.postin.api.apix.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;

import java.util.List;

@ApiModel
public class ApixQuery {
    @ApiProperty(name = "name",desc = "模糊查询")
    private String name;

    @ApiProperty(name ="categoryId",desc = "分类id")
    private String categoryId;

    @ApiProperty(name="protocolType",desc = "协议类型")
    private String protocolType;

    @ApiProperty(name ="workspaceId",desc = "空间id")
    private String workspaceId;

    @ApiProperty(name ="apiUid",desc = "apiUid")
    private String apiUid;

    @ApiProperty(name ="version",desc = "version")
    private String version;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().desc("createTime").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    public String getCategoryId() {
        return categoryId;
    }

    public ApixQuery setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public String getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(String workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    public List<Order> getOrderParams() {
            return orderParams;
        }

    public void setOrderParams(List<Order> orderParams) {
        this.orderParams = orderParams;
    }

    public Page getPageParam() {
        return pageParam;
    }

    public void setPageParam(Page pageParam) {
            this.pageParam = pageParam;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApiUid() {
        return apiUid;
    }

    public ApixQuery setApiUid(String apiUid) {
        this.apiUid = apiUid;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}