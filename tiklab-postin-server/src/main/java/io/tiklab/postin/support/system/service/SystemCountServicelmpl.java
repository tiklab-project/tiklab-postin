package io.tiklab.postin.support.system.service;

import io.tiklab.message.message.service.MessageNoticeService;
import io.tiklab.message.setting.service.MessageSendTypeService;
import io.tiklab.postin.support.system.model.SystemCount;
import io.tiklab.privilege.role.service.RoleService;
import io.tiklab.user.directory.service.UserDirService;
import io.tiklab.user.orga.service.OrgaService;
import io.tiklab.user.user.service.UserService;
import io.tiklab.user.usergroup.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemCountServicelmpl implements SystemCountService {

    @Autowired
    UserService userService;

    @Autowired
    OrgaService orgaService;

    @Autowired
    UserGroupService userGroupService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserDirService userDirService;

    @Autowired
    MessageNoticeService messageNoticeService;

    @Autowired
    MessageSendTypeService messageSendTypeService;

    @Override
    public SystemCount getSystemCount() {
        Integer userNumber = userService.findUserNumber();
        Integer orgaNumber = orgaService.findOrgaNumber();
        Integer userGroupNumber = userGroupService.findUserGroupNumber();
        Integer userDirNumber = userDirService.findUserDirNumber();
        Integer roleNumber = roleService.findRoleNumber();
        Integer noticeNumber = messageNoticeService.findNoticeNumber("postin");
        Integer sendTypeNumber = messageSendTypeService.findSendTypeNumber();


        SystemCount systemCount = new SystemCount();
        systemCount.setUserCount(userNumber);
        systemCount.setUserGroupCount(userGroupNumber);
        systemCount.setUserDirCount(userDirNumber);
        systemCount.setOrgaCount(orgaNumber);
        systemCount.setRoleCount(roleNumber);
        systemCount.setMsgNoticeCount(noticeNumber);
        systemCount.setMsgSendTypeCount(sendTypeNumber);

        return systemCount;
    }
}
































