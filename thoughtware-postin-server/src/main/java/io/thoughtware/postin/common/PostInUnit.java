package io.thoughtware.postin.common;

import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
import io.thoughtware.eam.common.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    /**
     * 生成12位id
     * @return
     */
    public String generateId() {
        String uuid = UUID.randomUUID().toString();
        String id = uuid.trim().replaceAll("-", "");

        // 获取rid中的前12位
        return id.substring(0,12);
    }

}
