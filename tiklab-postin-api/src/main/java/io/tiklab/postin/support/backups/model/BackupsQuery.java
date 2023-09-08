package io.tiklab.postin.support.backups.model;

import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;

import java.util.List;

public class BackupsQuery {

    private String type;


    // 运行状态 run success error
    private String runState;

    // 定时同步
    private Boolean scheduled = false;


    private List<Order> orderParams = OrderBuilders.instance().desc("createTime").get();


    private Page pageParam = new Page();


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRunState() {
        return runState;
    }

    public void setRunState(String runState) {
        this.runState = runState;
    }

    public Boolean getScheduled() {
        return scheduled;
    }

    public void setScheduled(Boolean scheduled) {
        this.scheduled = scheduled;
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
}
