package io.tiklab.postin.autotest.http.perf.instance.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.postin.autotest.http.perf.instance.dao.ApiPerfInstanceReportDao;
import io.tiklab.postin.autotest.http.perf.instance.entity.ApiPerfInstanceReportEntity;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfInstanceReport;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfInstanceReportQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* 接口性能历史实例 服务
*/
@Service
public class ApiPerfInstanceReportServiceImpl implements ApiPerfInstanceReportService {

    @Autowired
    ApiPerfInstanceReportDao apiPerfInstanceReportDao;

    @Autowired
    JoinTemplate joinTemplate;
    
    @Override
    public String createApiPerfInstanceReport(@NotNull @Valid ApiPerfInstanceReport apiPerfInstanceReport) {
        ApiPerfInstanceReportEntity apiPerfInstanceReportEntity = BeanMapper.map(apiPerfInstanceReport, ApiPerfInstanceReportEntity.class);
        
        return apiPerfInstanceReportDao.createApiPerfInstanceReport(apiPerfInstanceReportEntity);
    }

    @Override
    public void updateApiPerfInstanceReport(@NotNull @Valid ApiPerfInstanceReport apiPerfInstanceReport) {
        ApiPerfInstanceReportEntity apiPerfInstanceReportEntity = BeanMapper.map(apiPerfInstanceReport, ApiPerfInstanceReportEntity.class);

        apiPerfInstanceReportDao.updateApiPerfInstanceReport(apiPerfInstanceReportEntity);
    }

    @Override
    public void deleteApiPerfInstanceReport(@NotNull String id) {
        apiPerfInstanceReportDao.deleteApiPerfInstanceReport(id);
    }

    @Override
    public ApiPerfInstanceReport findOne(String id) {
        ApiPerfInstanceReportEntity apiPerfInstanceReportEntity = apiPerfInstanceReportDao.findApiPerfInstanceReport(id);

        ApiPerfInstanceReport apiPerfInstanceReport = BeanMapper.map(apiPerfInstanceReportEntity, ApiPerfInstanceReport.class);
        return apiPerfInstanceReport;
    }

    @Override
    public List<ApiPerfInstanceReport> findList(List<String> idList) {
        List<ApiPerfInstanceReportEntity> apiPerfInstanceReportEntities =  apiPerfInstanceReportDao.findApiPerfInstanceReportList(idList);

        List<ApiPerfInstanceReport> apiPerfInstanceReportList =  BeanMapper.mapList(apiPerfInstanceReportEntities, ApiPerfInstanceReport.class);
        return apiPerfInstanceReportList;
    }

    @Override
    public ApiPerfInstanceReport findApiPerfInstanceReport(@NotNull String id) {
        ApiPerfInstanceReport apiPerfInstanceReport = findOne(id);
        
        return apiPerfInstanceReport;
    }

    @Override
    public List<ApiPerfInstanceReport> findAllApiPerfInstanceReport() {
        List<ApiPerfInstanceReportEntity> apiPerfInstanceReportEntities =  apiPerfInstanceReportDao.findAllApiPerfInstanceReport();

        List<ApiPerfInstanceReport> apiPerfInstanceReportList =  BeanMapper.mapList(apiPerfInstanceReportEntities, ApiPerfInstanceReport.class);

        joinTemplate.joinQuery(apiPerfInstanceReportList);
        return apiPerfInstanceReportList;
    }

    @Override
    public List<ApiPerfInstanceReport> findApiPerfInstanceReportList(ApiPerfInstanceReportQuery performanceInstanceQuery) {
        List<ApiPerfInstanceReportEntity> apiPerfInstanceReportEntities = apiPerfInstanceReportDao.findApiPerfInstanceReportList(performanceInstanceQuery);

        List<ApiPerfInstanceReport> apiPerfInstanceReportList = BeanMapper.mapList(apiPerfInstanceReportEntities, ApiPerfInstanceReport.class);

        joinTemplate.joinQuery(apiPerfInstanceReportList);

        return apiPerfInstanceReportList;
    }

    @Override
    public Pagination<ApiPerfInstanceReport> findApiPerfInstanceReportPage(ApiPerfInstanceReportQuery performanceInstanceQuery) {
        Pagination<ApiPerfInstanceReportEntity>  pagination = apiPerfInstanceReportDao.findApiPerfInstanceReportPage(performanceInstanceQuery);

        List<ApiPerfInstanceReport> apiPerfInstanceReportList = BeanMapper.mapList(pagination.getDataList(), ApiPerfInstanceReport.class);

        joinTemplate.joinQuery(apiPerfInstanceReportList);

        return PaginationBuilder.build(pagination,apiPerfInstanceReportList);
    }
}