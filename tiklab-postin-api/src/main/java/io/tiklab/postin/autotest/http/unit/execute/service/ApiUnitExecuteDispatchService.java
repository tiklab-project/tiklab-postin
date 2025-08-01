package io.tiklab.postin.autotest.http.unit.execute.service;


import io.tiklab.postin.autotest.http.unit.execute.model.ApiUnitTestRequest;
import io.tiklab.postin.autotest.http.unit.instance.model.ApiUnitInstance;

/**
 * api单元用例测试 服务接口
 */
public interface ApiUnitExecuteDispatchService {

    /**
     * 执行
     * @param apiUnitTestRequest
     * @return
     */
    ApiUnitInstance execute(ApiUnitTestRequest apiUnitTestRequest);

    ApiUnitInstance executeStart(ApiUnitTestRequest apiUnitTestRequest);

}
