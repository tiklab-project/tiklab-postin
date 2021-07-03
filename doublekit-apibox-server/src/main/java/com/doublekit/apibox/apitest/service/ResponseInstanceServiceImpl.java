package com.doublekit.apibox.apitest.service;

import com.doublekit.apibox.apitest.dao.ResponseInstanceDao;
import com.doublekit.apibox.apitest.entity.ResponseInstancePo;
import com.doublekit.apibox.apitest.model.ResponseInstance;
import com.doublekit.apibox.apitest.model.ResponseInstanceQuery;

import com.doublekit.common.Pagination;
import com.doublekit.beans.BeanMapper;
import com.doublekit.join.join.JoinQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
* 用户服务业务处理
*/
@Service
public class ResponseInstanceServiceImpl implements ResponseInstanceService {

    @Autowired
    ResponseInstanceDao responseInstanceDao;

    @Autowired
    JoinQuery joinQuery;

    @Override
    public String createResponseInstance(@NotNull @Valid ResponseInstance responseInstance) {
        ResponseInstancePo responseInstancePo = BeanMapper.map(responseInstance, ResponseInstancePo.class);

        return responseInstanceDao.createResponseInstance(responseInstancePo);
    }

    @Override
    public void updateResponseInstance(@NotNull @Valid ResponseInstance responseInstance) {
        ResponseInstancePo responseInstancePo = BeanMapper.map(responseInstance, ResponseInstancePo.class);

        responseInstanceDao.updateResponseInstance(responseInstancePo);
    }

    @Override
    public void deleteResponseInstance(@NotNull String id) {
        responseInstanceDao.deleteResponseInstance(id);
    }

    @Override
    public ResponseInstance findOne(String id) {
        ResponseInstancePo responseInstancePo = responseInstanceDao.findResponseInstance(id);

        ResponseInstance responseInstance = BeanMapper.map(responseInstancePo, ResponseInstance.class);
        return responseInstance;
    }

    @Override
    public List<ResponseInstance> findList(List<String> idList) {
        List<ResponseInstancePo> responseInstancePoList =  responseInstanceDao.findResponseInstanceList(idList);

        List<ResponseInstance> responseInstanceList =  BeanMapper.mapList(responseInstancePoList,ResponseInstance.class);
        return responseInstanceList;
    }

    @Override
    public ResponseInstance findResponseInstance(@NotNull String id) {
        ResponseInstance responseInstance = findOne(id);

        joinQuery.queryOne(responseInstance);
        return responseInstance;
    }

    @Override
    public List<ResponseInstance> findAllResponseInstance() {
        List<ResponseInstancePo> responseInstancePoList =  responseInstanceDao.findAllResponseInstance();

        List<ResponseInstance> responseInstanceList =  BeanMapper.mapList(responseInstancePoList,ResponseInstance.class);

        joinQuery.queryList(responseInstanceList);
        return responseInstanceList;
    }

    @Override
    public List<ResponseInstance> findResponseInstanceList(ResponseInstanceQuery responseInstanceQuery) {
        List<ResponseInstancePo> responseInstancePoList = responseInstanceDao.findResponseInstanceList(responseInstanceQuery);

        List<ResponseInstance> responseInstanceList = BeanMapper.mapList(responseInstancePoList,ResponseInstance.class);

        joinQuery.queryList(responseInstanceList);

        return responseInstanceList;
    }

    @Override
    public Pagination<ResponseInstance> findResponseInstancePage(ResponseInstanceQuery responseInstanceQuery) {
        Pagination<ResponseInstance> pg = new Pagination<>();

        Pagination<ResponseInstancePo>  pagination = responseInstanceDao.findResponseInstancePage(responseInstanceQuery);
        BeanUtils.copyProperties(pagination,pg);

        List<ResponseInstance> responseInstanceList = BeanMapper.mapList(pagination.getDataList(),ResponseInstance.class);

        joinQuery.queryList(responseInstanceList);

        pg.setDataList(responseInstanceList);
        return pg;
    }
}