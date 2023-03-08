package io.tiklab.postin.workspace.service;


import io.tiklab.postin.api.apix.model.Apix;
import io.tiklab.postin.api.apix.model.ApixQuery;
import io.tiklab.postin.api.apix.service.ApixService;
import io.tiklab.postin.api.http.test.cases.model.HttpTestcase;
import io.tiklab.postin.api.http.test.cases.model.HttpTestcaseQuery;
import io.tiklab.postin.api.http.test.cases.service.HttpTestcaseService;
import io.tiklab.postin.category.model.Category;
import io.tiklab.postin.category.model.CategoryQuery;
import io.tiklab.postin.category.service.CategoryService;
import io.tiklab.postin.support.datastructure.model.DataStructure;
import io.tiklab.postin.support.datastructure.model.DataStructureQuery;
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
    CategoryService categoryService;

    @Autowired
    ApixService apixService;

    @Autowired
    HttpTestcaseService httpTestcaseService;

    @Autowired
    DataStructureService dataStructureService;

    @Autowired
    DmUserService dmUserService;




    @Override
    public WorkspaceTotal findWorkspaceOverview(String id) {
        WorkspaceTotal workspaceTotal = new WorkspaceTotal();

        //获取分组的总和
        List<Category> categoryList = categoryService.findCategoryList(new CategoryQuery().setWorkspaceId(id));
        workspaceTotal.setCategoryTotal(categoryList.size());

        //获取接口总和，case总和
        int apiCount=0;
        int caseCount = 0;
        for(Category category :categoryList){
            List<Apix>  apixList = apixService.findApixList(new ApixQuery().setCategoryId(category.getId()));
            apiCount+=apixList.size();

            int apiCaseCount=0;
            for (Apix apix: apixList){
                List<HttpTestcase> testcaseList = httpTestcaseService.findTestcaseList(new HttpTestcaseQuery().setHttpId(apix.getId()));
                apiCaseCount+=testcaseList.size();
            }

            caseCount+=apiCaseCount;
        }

        List<DataStructure> dataStructureList = dataStructureService.findDataStructureList(new DataStructureQuery().setWorkspaceId(id));

        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setDomainId(id);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);

        workspaceTotal.setModelTotal(dataStructureList.size());
        workspaceTotal.setApiTotal(apiCount);
        workspaceTotal.setCaseTotal(caseCount);
        workspaceTotal.setMemberTotal(dmUserList.size());



        return workspaceTotal;
    }

}