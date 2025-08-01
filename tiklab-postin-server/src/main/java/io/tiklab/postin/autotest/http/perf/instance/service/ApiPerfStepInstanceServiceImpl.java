package io.tiklab.postin.autotest.http.perf.instance.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.postin.autotest.http.perf.instance.dao.ApiPerfStepInstanceDao;
import io.tiklab.postin.autotest.http.perf.instance.entity.ApiPerfStepInstanceEntity;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfStepInstance;
import io.tiklab.postin.autotest.http.perf.instance.model.ApiPerfStepInstanceQuery;
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
public class ApiPerfStepInstanceServiceImpl implements ApiPerfStepInstanceService {

    @Autowired
    ApiPerfStepInstanceDao ApiPerfStepInstanceDao;

    @Autowired
    ApiPerfStepUnitCalcService apiPerfStepUnitCalcService;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createApiPerfStepInstance(@NotNull @Valid ApiPerfStepInstance perfInstance) {
        ApiPerfStepInstanceEntity perfInstanceEntity = BeanMapper.map(perfInstance, ApiPerfStepInstanceEntity.class);

        return ApiPerfStepInstanceDao.createApiPerfStepInstance(perfInstanceEntity);
    }

    @Override
    public void updateApiPerfStepInstance(@NotNull @Valid ApiPerfStepInstance perfInstance) {
        ApiPerfStepInstanceEntity perfInstanceEntity = BeanMapper.map(perfInstance, ApiPerfStepInstanceEntity.class);

        ApiPerfStepInstanceDao.updateApiPerfStepInstance(perfInstanceEntity);
    }

    @Override
    public void deleteApiPerfStepInstance(@NotNull String id) {
        ApiPerfStepInstanceDao.deleteApiPerfStepInstance(id);

        apiPerfStepUnitCalcService.deleteAllApiPerfStepUnitCalc(id);
    }

    @Override
    public ApiPerfStepInstance findOne(String id) {
        ApiPerfStepInstanceEntity perfInstanceEntity = ApiPerfStepInstanceDao.findApiPerfStepInstance(id);

        ApiPerfStepInstance perfInstance = BeanMapper.map(perfInstanceEntity, ApiPerfStepInstance.class);
        return perfInstance;
    }

    @Override
    public List<ApiPerfStepInstance> findList(List<String> idList) {
        List<ApiPerfStepInstanceEntity> perfInstanceEntities =  ApiPerfStepInstanceDao.findApiPerfStepInstanceList(idList);

        List<ApiPerfStepInstance> perfInstanceList =  BeanMapper.mapList(perfInstanceEntities, ApiPerfStepInstance.class);
        return perfInstanceList;
    }

    @Override
    public ApiPerfStepInstance findApiPerfStepInstance(@NotNull String id) {
        ApiPerfStepInstance perfInstance = findOne(id);

        joinTemplate.joinQuery(perfInstance);

        ApiPerfStepUnitCalcQuery apiPerfStepUnitCalcQuery = new ApiPerfStepUnitCalcQuery();
        apiPerfStepUnitCalcQuery.setApiPerfStepInstanceId(id);
        List<ApiPerfStepUnitCalc> apiPerfStepUnitCalcList = apiPerfStepUnitCalcService.findApiPerfStepUnitCalcList(apiPerfStepUnitCalcQuery);
        perfInstance.setApiPerfStepUnitCalcList(apiPerfStepUnitCalcList);

        return perfInstance;
    }

    @Override
    public List<ApiPerfStepInstance> findAllApiPerfStepInstance() {
        List<ApiPerfStepInstanceEntity> perfInstanceEntities =  ApiPerfStepInstanceDao.findAllApiPerfStepInstance();

        List<ApiPerfStepInstance> perfInstanceList =  BeanMapper.mapList(perfInstanceEntities, ApiPerfStepInstance.class);

        joinTemplate.joinQuery(perfInstanceList);
        return perfInstanceList;
    }

    @Override
    public List<ApiPerfStepInstance> findApiPerfStepInstanceList(ApiPerfStepInstanceQuery performanceInstanceQuery) {
        List<ApiPerfStepInstanceEntity> perfInstanceEntities = ApiPerfStepInstanceDao.findApiPerfStepInstanceList(performanceInstanceQuery);
        List<ApiPerfStepInstance> perfInstanceList = BeanMapper.mapList(perfInstanceEntities, ApiPerfStepInstance.class);
        joinTemplate.joinQuery(perfInstanceList);

        if(perfInstanceList.size() > 0){
            for(ApiPerfStepInstance apiPerfStepInstance:perfInstanceList){
                ApiPerfStepUnitCalcQuery apiPerfStepUnitCalcQuery = new ApiPerfStepUnitCalcQuery();
                apiPerfStepUnitCalcQuery.setApiPerfStepInstanceId(apiPerfStepInstance.getId());
                List<ApiPerfStepUnitCalc> apiPerfStepUnitCalcList = apiPerfStepUnitCalcService.findApiPerfStepUnitCalcList(apiPerfStepUnitCalcQuery);
                apiPerfStepInstance.setApiPerfStepUnitCalcList(apiPerfStepUnitCalcList);
            }
        }

        return perfInstanceList;
    }

    @Override
    public Pagination<ApiPerfStepInstance> findApiPerfStepInstancePage(ApiPerfStepInstanceQuery performanceInstanceQuery) {
        Pagination<ApiPerfStepInstanceEntity>  pagination = ApiPerfStepInstanceDao.findApiPerfStepInstancePage(performanceInstanceQuery);

        List<ApiPerfStepInstance> perfInstanceList = BeanMapper.mapList(pagination.getDataList(), ApiPerfStepInstance.class);

        joinTemplate.joinQuery(perfInstanceList);

        return PaginationBuilder.build(pagination,perfInstanceList);
    }
}