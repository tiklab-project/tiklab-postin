package com.doublekit.apibox.search.controller;

import com.doublekit.apibox.annotation.Api;
import com.doublekit.apibox.annotation.ApiMethod;
import com.doublekit.apibox.annotation.ApiParam;
import com.doublekit.apibox.apidef.http.model.HttpApi;
import com.doublekit.apibox.search.service.SearchService;
import com.doublekit.apibox.workspace.model.Workspace;
import com.doublekit.core.Result;
import com.doublekit.core.exception.ApplicationException;
import com.doublekit.dss.common.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * ManagerController
 * Created by Zhangzhihua on 2017/9/25.
 */
@RestController
@RequestMapping("/search")
@Api(name = "SearchController",desc = "搜索管理")
public class SearchController {

    private static Logger logger = LoggerFactory.getLogger(SearchController.class);

    //索引->实体类映射表
    private static Map<String,Class<?>> indexMapping = new HashMap<>();

    @Autowired
    SearchService searchService;


    @RequestMapping(path="/rebuild",method = RequestMethod.POST)
    public Result<Void> rebuild(){
        searchService.rebuild();
        return Result.ok();
    }


    @RequestMapping(path="/get",method = RequestMethod.POST)
    @ApiMethod(name = "get",desc = "根据ID查找")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Map<String,Object>> get(@NotNull String id){
        Map<String,Object> map = searchService.findOne(HttpApi.class,id);

        return Result.ok(map);
    }


    @RequestMapping(path="/searchForTop",method = RequestMethod.POST)
    @ApiMethod(name = "searchForTop",desc = "根据关键字搜索")
    @ApiParam(name = "keyword",desc = "关键字",required = true)
    public Result<AllTopResponse> searchForTop(@NotNull String keyword){
        AllTopResponse allTopResponse = new AllTopResponse();

        //搜索项目
        TopResponse topResponse = searchService.searchForTop(Workspace.class,keyword);
        if(topResponse != null && topResponse.getTotalRecord() > 0){
            allTopResponse.getResponseList().add(topResponse);
        }

        //搜索事项
        topResponse = searchService.searchForTop(HttpApi.class,keyword);
        if(topResponse != null && topResponse.getTotalRecord() > 0){
            allTopResponse.getResponseList().add(topResponse);
        }

        return Result.ok(allTopResponse);
    }


    @RequestMapping(path="/searchForCount",method = RequestMethod.POST)
    @ApiMethod(name = "searchForCount",desc = "统计搜索结果")
    @ApiParam(name = "keyword",desc = "关键字",required = true)
    public Result<AllCountResponse> searchForCount(@NotNull String keyword){
        AllCountResponse allCountResponse = new AllCountResponse();

        //搜索项目
        CountResponse countResponse = searchService.searchForCount(Workspace.class,keyword);
        allCountResponse.getResponseList().add(countResponse);

        //搜索事项
        countResponse = searchService.searchForCount(HttpApi.class,keyword);
        allCountResponse.getResponseList().add(countResponse);

        return Result.ok(allCountResponse);
    }


    @RequestMapping(path="/searchForPage",method = RequestMethod.POST)
    @ApiMethod(name = "searchForPage",desc = "按索引名称、关键字分页搜索")
    @ApiParam(name = "pageRequest",desc = "分页搜索条件",required = true)
    public Result<PageResponse> searchForPage(@RequestBody @Valid @NotNull PageRequest pageRequest){
        String index = pageRequest.getIndex();
        Class entityClass = getEntityClass(index);
        if(entityClass == null){
            throw new ApplicationException("not found index:" + index);
        }
        String keyword = pageRequest.getKeyword();
        PageCondition pageCondition = pageRequest.getPageCondition();
        if(pageCondition == null){
            pageCondition = PageCondition.instance();
        }

        //分页搜索
        PageResponse pageResponse = searchService.searchForPage(entityClass,keyword,pageCondition);
        return Result.ok(pageResponse);
    }

    /**
     * 根据索引名称查找对应的实体类
     * @param index
     * @return
     */
    Class getEntityClass(String index){
        Class entityClass = indexMapping.get(index);
        if(entityClass == null){
            if("workspace".equalsIgnoreCase(index)){
                indexMapping.put(index,Workspace.class);
            }else if("methodex".equalsIgnoreCase(index)){
                indexMapping.put(index, HttpApi.class);
            }else{
                throw new ApplicationException("unsupport index type:" + index);
            }
            entityClass = indexMapping.get(index);
        }
        return entityClass;
    }

}
