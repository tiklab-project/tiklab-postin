package io.tiklab.postin.api.http.document.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.tiklab.postin.api.http.document.dao.ShareNodeDao;
import io.tiklab.postin.api.http.document.entity.ShareNodeEntity;
import io.tiklab.postin.api.http.document.model.ShareNode;
import io.tiklab.postin.api.http.document.model.ShareNodeQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 分享绑定的节点 服务
*/
@Service
public class ShareNodeServiceImpl implements ShareNodeService {

    @Autowired
    ShareNodeDao shareNodeDao;

    @Autowired
    JoinTemplate joinTemplate;


    @Override
    public String createShareNode(@NotNull @Valid ShareNode shareNode) {
        ShareNodeEntity shareNodeEntity = BeanMapper.map(shareNode, ShareNodeEntity.class);

        return shareNodeDao.createShareNode(shareNodeEntity);
    }

    @Override
    public void updateShareNode(@NotNull @Valid ShareNode shareNode) {
        ShareNodeEntity shareNodeEntity = BeanMapper.map(shareNode, ShareNodeEntity.class);

        shareNodeDao.updateShareNode(shareNodeEntity);
    }

    @Override
    public void deleteShareNode(@NotNull String id) {
        shareNodeDao.deleteShareNode(id);
    }

    @Override
    public void deleteShareNodeByApiId(String shareId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(ShareNodeEntity.class)
                .eq("shareId", shareId)
                .get();
        shareNodeDao.deleteShareNode(deleteCondition);
    }

    @Override
    public ShareNode findOne(String id) {
        ShareNodeEntity shareNodeEntity = shareNodeDao.findShareNode(id);

        ShareNode shareNode = BeanMapper.map(shareNodeEntity, ShareNode.class);
        return shareNode;
    }

    @Override
    public List<ShareNode> findList(List<String> idList) {
        List<ShareNodeEntity> shareNodeEntityList =  shareNodeDao.findShareNodeList(idList);

        List<ShareNode> shareNodeList =  BeanMapper.mapList(shareNodeEntityList,ShareNode.class);
        return shareNodeList;
    }

    @Override
    public ShareNode findShareNode(@NotNull String id) {
        ShareNode shareNode = findOne(id);

        joinTemplate.joinQuery(shareNode);

        return shareNode;
    }

    @Override
    public List<ShareNode> findAllShareNode() {
        List<ShareNodeEntity> shareNodeEntityList =  shareNodeDao.findAllShareNode();

        List<ShareNode> shareNodeList =  BeanMapper.mapList(shareNodeEntityList,ShareNode.class);

        joinTemplate.joinQuery(shareNodeList);
        return shareNodeList;
    }

    @Override
    public List<ShareNode> findShareNodeList(ShareNodeQuery shareNodeQuery) {
        List<ShareNodeEntity> shareNodeEntityList = shareNodeDao.findShareNodeList(shareNodeQuery);

        List<ShareNode> shareNodeList = BeanMapper.mapList(shareNodeEntityList,ShareNode.class);

        joinTemplate.joinQuery(shareNodeList);

        return shareNodeList;
    }

    @Override
    public Pagination<ShareNode> findShareNodePage(ShareNodeQuery shareNodeQuery) {
        Pagination<ShareNodeEntity>  pagination = shareNodeDao.findShareNodePage(shareNodeQuery);

        List<ShareNode> shareNodeList = BeanMapper.mapList(pagination.getDataList(),ShareNode.class);

        joinTemplate.joinQuery(shareNodeList);

        return PaginationBuilder.build(pagination,shareNodeList);
    }


}