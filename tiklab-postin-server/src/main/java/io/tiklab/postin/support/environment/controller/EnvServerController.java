package io.tiklab.postin.support.environment.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.postin.support.environment.model.EnvServer;
import io.tiklab.postin.support.environment.model.EnvServerQuery;
import io.tiklab.postin.support.environment.service.EnvServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 环境中服务地址 控制器
 */
@RestController
@RequestMapping("/envServer")
@Api(name = "EnvServerController",desc = "环境中服务地址管理")
public class EnvServerController {

    private static Logger logger = LoggerFactory.getLogger(EnvServerController.class);

    @Autowired
    private EnvServerService envServerService;

    @RequestMapping(path="/createEnvServer",method = RequestMethod.POST)
    @ApiMethod(name = "createEnvServer",desc = "创建环境中服务地址")
    @ApiParam(name = "envServer",desc = "envServer",required = true)
    public Result<String> createEnvServer(@RequestBody @NotNull @Valid EnvServer envServer){
        String id = envServerService.createEnvServer(envServer);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateEnvServer",method = RequestMethod.POST)
    @ApiMethod(name = "updateEnvServer",desc = "更新环境中服务地址")
    @ApiParam(name = "envServer",desc = "envServer",required = true)
    public Result<Void> updateEnvServer(@RequestBody @NotNull @Valid EnvServer envServer){
        envServerService.updateEnvServer(envServer);

        return Result.ok();
    }

    @RequestMapping(path="/deleteEnvServer",method = RequestMethod.POST)
    @ApiMethod(name = "deleteEnvServer",desc = "删除环境中服务地址")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteEnvServer(@NotNull String id){
        envServerService.deleteEnvServer(id);

        return Result.ok();
    }


    @RequestMapping(path="/findEnvServer",method = RequestMethod.POST)
    @ApiMethod(name = "findEnvServer",desc = "通过id查找环境中服务地址")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<EnvServer> findEnvServer(@NotNull String id){
        EnvServer envServer = envServerService.findEnvServer(id);

        return Result.ok(envServer);
    }

    @RequestMapping(path="/findAllEnvServer",method = RequestMethod.POST)
    @ApiMethod(name = "findAllEnvServer",desc = "查找所有环境中服务地址")
    public Result<List<EnvServer>> findAllEnvServer(){
        List<EnvServer> envServerList = envServerService.findAllEnvServer();

        return Result.ok(envServerList);
    }


    @RequestMapping(path = "/findEnvServerList",method = RequestMethod.POST)
    @ApiMethod(name = "findEnvServerList",desc = "根据查询参数查找环境中服务地址")
    @ApiParam(name = "envServerQuery",desc = "envServerQuery",required = true)
    public Result<List<EnvServer>> findEnvServerList(@RequestBody @Valid @NotNull EnvServerQuery envServerQuery){
        List<EnvServer> envServerList = envServerService.findEnvServerList(envServerQuery);

        return Result.ok(envServerList);
    }


    @RequestMapping(path = "/findEnvServerPage",method = RequestMethod.POST)
    @ApiMethod(name = "findEnvServerPage",desc = "根据查询参数按分页查找环境中服务地址")
    @ApiParam(name = "envServerQuery",desc = "envServerQuery",required = true)
    public Result<Pagination<EnvServer>> findEnvServerPage(@RequestBody @Valid @NotNull EnvServerQuery envServerQuery){
        Pagination<EnvServer> pagination = envServerService.findEnvServerPage(envServerQuery);

        return Result.ok(pagination);
    }

}
