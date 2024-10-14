package io.tiklab.postin.workspace.service;

import io.tiklab.postin.api.apix.service.ApixService;
import io.tiklab.postin.category.service.CategoryService;
import io.tiklab.postin.support.datastructure.service.DataStructureService;
import io.tiklab.postin.workspace.model.*;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
import io.tiklab.user.dmUser.service.DmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* 空间概要 服务
*/
@Service
public class WorkspaceOverviewServiceImpl implements WorkspaceOverviewService {

    @Autowired
    DmUserService dmUserService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ApixService apixService;

    @Autowired
    DataStructureService dataStructureService;


    @Override
    public WorkspaceTotal findWorkspaceOverview(String workspaceId) {

        WorkspaceTotal workspaceTotal = new WorkspaceTotal();

        //获取分组的总数
        int categoryNum = categoryService.findCategoryNum(workspaceId);
        workspaceTotal.setCategoryTotal(categoryNum);

        //获取接口总数
        int apiCount= apixService.findApixNum(workspaceId);
        workspaceTotal.setApiTotal(apiCount);

        int modelNum = dataStructureService.findModelNum(workspaceId);
        workspaceTotal.setModelTotal(modelNum);

        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setDomainId(workspaceId);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);

        workspaceTotal.setMemberTotal(dmUserList.size());

        return workspaceTotal;
    }

}