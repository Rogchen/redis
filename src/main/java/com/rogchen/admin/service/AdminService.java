package com.rogchen.admin.service;

import com.rogchen.admin.domain.AdminUser;
import com.rogchen.common.Page;

import java.util.Map;

/**
 * @Author Rogchen{cbs@xmyr.cn}
 * Created by chbs on 2017/4/6.
 * {tags}
 */
public interface AdminService {

    AdminUser getAdminUser(Long id);

    Page findPage(Page<AdminUser> adminUserPage, AdminUser adminUser, Map map);
}
