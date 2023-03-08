package io.tiklab.postin.common;

import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserService;
import io.tiklab.utils.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * postin 工具类
 */
@Service
public class PostInUnit {

    @Autowired
    UserService userService;

    /**
     * 获取当前用户的信息
     * @return
     */
    public User getUser(){
        String userId = LoginContext.getLoginId();

        return userService.findUser(userId);
    }
}
