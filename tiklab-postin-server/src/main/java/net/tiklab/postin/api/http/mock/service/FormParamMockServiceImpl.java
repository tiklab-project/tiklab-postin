package net.tiklab.postin.api.http.mock.service;

import net.tiklab.postin.api.http.mock.dao.FormParamMockDao;
import net.tiklab.postin.api.http.mock.entity.FormParamMockEntity;
import net.tiklab.postin.api.http.mock.model.FormParamMock;
import net.tiklab.postin.api.http.mock.model.FormParamMockQuery;

import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 用户服务业务处理
*/
@Service
public class FormParamMockServiceImpl implements FormParamMockService {

    @Autowired
    FormParamMockDao formParamMockDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createFormParamMock(@NotNull @Valid FormParamMock formParamMock) {
        FormParamMockEntity formParamMockEntity = BeanMapper.map(formParamMock, FormParamMockEntity.class);

        return formParamMockDao.createFormParamMock(formParamMockEntity);
    }

    @Override
    public void updateFormParamMock(@NotNull @Valid FormParamMock formParamMock) {
        FormParamMockEntity formParamMockEntity = BeanMapper.map(formParamMock, FormParamMockEntity.class);

        formParamMockDao.updateFormParamMock(formParamMockEntity);
    }

    @Override
    public void deleteFormParamMock(@NotNull String id) {
        formParamMockDao.deleteFormParamMock(id);
    }

    @Override
    public FormParamMock findFormParamMock(@NotNull String id) {
        FormParamMockEntity formParamMockEntity = formParamMockDao.findFormParamMock(id);

        FormParamMock formParamMock = BeanMapper.map(formParamMockEntity, FormParamMock.class);

        joinTemplate.joinQuery(formParamMock);

        return formParamMock;
    }

    @Override
    public List<FormParamMock> findAllFormParamMock() {
        List<FormParamMockEntity> formParamMockEntityList =  formParamMockDao.findAllFormParamMock();

        List<FormParamMock> formParamMockList =  BeanMapper.mapList(formParamMockEntityList,FormParamMock.class);

        joinTemplate.joinQuery(formParamMockList);

        return formParamMockList;
    }

    @Override
    public List<FormParamMock> findFormParamMockList(FormParamMockQuery formParamMockQuery) {
        List<FormParamMockEntity> formParamMockEntityList = formParamMockDao.findFormParamMockList(formParamMockQuery);

        List<FormParamMock> formParamMockList = BeanMapper.mapList(formParamMockEntityList,FormParamMock.class);

        joinTemplate.joinQuery(formParamMockList);

        return formParamMockList;
    }

    @Override
    public Pagination<FormParamMock> findFormParamMockPage(FormParamMockQuery formParamMockQuery) {

        Pagination<FormParamMockEntity>  pagination = formParamMockDao.findFormParamMockPage(formParamMockQuery);

        List<FormParamMock> formParamMockList = BeanMapper.mapList(pagination.getDataList(),FormParamMock.class);

        joinTemplate.joinQuery(formParamMockList);

        return PaginationBuilder.build(pagination,formParamMockList);
    }
}