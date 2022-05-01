package com.doublekit.apibox.apidef.apix.service;

import com.doublekit.common.page.Pagination;

import com.doublekit.apibox.apidef.apix.model.Apix;
import com.doublekit.apibox.apidef.apix.model.ApixQuery;
import com.doublekit.join.annotation.FindAll;
import com.doublekit.join.annotation.FindList;
import com.doublekit.join.annotation.FindOne;
import com.doublekit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* BasedefService
*/
@JoinProvider(model = Apix.class)
public interface ApixService {

    /**
    * 创建
    * @param apix
    * @return
    */
    String createApix(@NotNull @Valid Apix apix);

    /**
    * 更新
    * @param apix
    */
    void updateApix(@NotNull @Valid Apix apix);

    /**
    * 删除
    * @param id
    */
    void deleteApix(@NotNull String id);
    @FindOne
    Apix findOne(@NotNull String id);
    @FindList
    List<Apix> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    Apix findApix(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<Apix> findAllApix();

    /**
    * 查询列表
    * @param apixQuery
    * @return
    */
    List<Apix> findApixList(ApixQuery apixQuery);

    /**
    * 按分页查询
    * @param apixQuery
    * @return
    */
    Pagination<Apix> findApixPage(ApixQuery apixQuery);

}