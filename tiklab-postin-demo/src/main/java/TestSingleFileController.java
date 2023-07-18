
import io.tiklab.core.Result;
import io.tiklab.postin.workspace.model.WorkspaceFollow;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.postin.workspace.service.WorkspaceFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @pi.protocol: http
 * @pi.groupName: TestSingleFile
 * @author sxc
 */
@RestController
@RequestMapping("/workspace")
@Api(name = "WorkspaceFollowController",desc = "WorkspaceFollowController")
public class TestSingleFileController {

    @Autowired
    private WorkspaceFollowService workspaceFollowService;

    /**
     * @pi.name:创建空间关注
     * @pi.path:/workspaceFollow/createWorkspaceFollow
     * @pi.method:post
     * @pi.request-type:json
     * @pi.param: model=WorkspaceFollow
     */
    @RequestMapping(path="/createWorkspaceFollow",method = RequestMethod.POST)
    @ApiMethod(name = "createWorkspaceFollow",desc = "创建空间关注")
    @ApiParam(name = "workspaceFollow",desc = "workspaceFollow",required = true)
    public Result<String> createWorkspaceFollow(@RequestBody @NotNull @Valid WorkspaceFollow workspaceFollow){
        String id = workspaceFollowService.createWorkspaceFollow(workspaceFollow);

        return Result.ok(id);
    }


}
