package io.tiklab.postin.workspace.service;

import io.tiklab.postin.workspace.dao.WorkspaceOverviewDao;
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
    WorkspaceOverviewDao workspaceOverviewDao;


    @Override
    public WorkspaceTotal findWorkspaceOverview(String id) {

        WorkspaceTotal workspaceTotal = workspaceOverviewDao.findWorkspaceOverview(id);

        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setDomainId(id);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);

        workspaceTotal.setMemberTotal(dmUserList.size());

        return workspaceTotal;
    }

}