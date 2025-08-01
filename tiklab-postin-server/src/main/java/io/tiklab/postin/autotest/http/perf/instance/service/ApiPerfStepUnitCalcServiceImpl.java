package io.tiklab.postin.autotest.http.perf.instance.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.postin.autotest.http.perf.instance.dao.ApiPerfStepUnitCalcDao;
import io.tiklab.postin.autotest.http.perf.instance.entity.ApiPerfStepUnitCalcEntity;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfStepUnitCalc;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfStepUnitCalcQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口性能历史实例 服务
*/
@Service
public class ApiPerfStepUnitCalcServiceImpl implements ApiPerfStepUnitCalcService {

    @Autowired
    ApiPerfStepUnitCalcDao ApiPerfStepUnitCalcDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createApiPerfStepUnitCalc(@NotNull @Valid ApiPerfStepUnitCalc perfInstance) {
        ApiPerfStepUnitCalcEntity perfInstanceEntity = BeanMapper.map(perfInstance, ApiPerfStepUnitCalcEntity.class);

        return ApiPerfStepUnitCalcDao.createApiPerfStepUnitCalc(perfInstanceEntity);
    }

    @Override
    public void updateApiPerfStepUnitCalc(@NotNull @Valid ApiPerfStepUnitCalc perfInstance) {
        ApiPerfStepUnitCalcEntity perfInstanceEntity = BeanMapper.map(perfInstance, ApiPerfStepUnitCalcEntity.class);

        ApiPerfStepUnitCalcDao.updateApiPerfStepUnitCalc(perfInstanceEntity);
    }

    @Override
    public void deleteApiPerfStepUnitCalc(@NotNull String id) {
        ApiPerfStepUnitCalcDao.deleteApiPerfStepUnitCalc(id);
    }

    @Override
    public void deleteAllApiPerfStepUnitCalc(String apiPerfStepInstanceId) {
        ApiPerfStepUnitCalcQuery apiPerfStepUnitCalcQuery = new ApiPerfStepUnitCalcQuery();
        apiPerfStepUnitCalcQuery.setApiPerfStepInstanceId(apiPerfStepInstanceId);
        List<ApiPerfStepUnitCalc> apiPerfStepUnitCalcList = findApiPerfStepUnitCalcList(apiPerfStepUnitCalcQuery);
        if(apiPerfStepUnitCalcList != null && !apiPerfStepUnitCalcList.isEmpty()){
            for(ApiPerfStepUnitCalc apiPerfStepUnitCalc : apiPerfStepUnitCalcList){
                deleteApiPerfStepUnitCalc(apiPerfStepUnitCalc.getId());
            }
        }
    }

    @Override
    public ApiPerfStepUnitCalc findOne(String id) {
        ApiPerfStepUnitCalcEntity perfInstanceEntity = ApiPerfStepUnitCalcDao.findApiPerfStepUnitCalc(id);

        ApiPerfStepUnitCalc perfInstance = BeanMapper.map(perfInstanceEntity, ApiPerfStepUnitCalc.class);
        return perfInstance;
    }

    @Override
    public List<ApiPerfStepUnitCalc> findList(List<String> idList) {
        List<ApiPerfStepUnitCalcEntity> perfInstanceEntities =  ApiPerfStepUnitCalcDao.findApiPerfStepUnitCalcList(idList);

        List<ApiPerfStepUnitCalc> perfInstanceList =  BeanMapper.mapList(perfInstanceEntities, ApiPerfStepUnitCalc.class);
        return perfInstanceList;
    }

    @Override
    public ApiPerfStepUnitCalc findApiPerfStepUnitCalc(@NotNull String id) {
        ApiPerfStepUnitCalc perfInstance = findOne(id);

        joinTemplate.joinQuery(perfInstance);



        return perfInstance;
    }

    @Override
    public List<ApiPerfStepUnitCalc> findAllApiPerfStepUnitCalc() {
        List<ApiPerfStepUnitCalcEntity> perfInstanceEntities =  ApiPerfStepUnitCalcDao.findAllApiPerfStepUnitCalc();

        List<ApiPerfStepUnitCalc> perfInstanceList =  BeanMapper.mapList(perfInstanceEntities, ApiPerfStepUnitCalc.class);

        joinTemplate.joinQuery(perfInstanceList);
        return perfInstanceList;
    }

    @Override
    public List<ApiPerfStepUnitCalc> findApiPerfStepUnitCalcList(ApiPerfStepUnitCalcQuery performanceInstanceQuery) {
        List<ApiPerfStepUnitCalcEntity> perfInstanceEntities = ApiPerfStepUnitCalcDao.findApiPerfStepUnitCalcList(performanceInstanceQuery);

        List<ApiPerfStepUnitCalc> perfInstanceList = BeanMapper.mapList(perfInstanceEntities, ApiPerfStepUnitCalc.class);

        joinTemplate.joinQuery(perfInstanceList);

        return perfInstanceList;
    }

    @Override
    public Pagination<ApiPerfStepUnitCalc> findApiPerfStepUnitCalcPage(ApiPerfStepUnitCalcQuery performanceInstanceQuery) {
        Pagination<ApiPerfStepUnitCalcEntity>  pagination = ApiPerfStepUnitCalcDao.findApiPerfStepUnitCalcPage(performanceInstanceQuery);

        List<ApiPerfStepUnitCalc> perfInstanceList = BeanMapper.mapList(pagination.getDataList(), ApiPerfStepUnitCalc.class);

        joinTemplate.joinQuery(perfInstanceList);

        return PaginationBuilder.build(pagination,perfInstanceList);
    }
}