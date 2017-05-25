package com.rogchen.admin.service.Impl;

import com.rogchen.admin.dao.AdminDao;
import com.rogchen.admin.domain.AdminUser;
import com.rogchen.admin.service.AdminService;
import com.rogchen.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author Rogchen{cbs@xmyr.cn}
 * Created by chbs on 2017/4/6.
 * {tags}
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public AdminUser getAdminUser(Long id) {
        return adminDao.getAdminUser(id);
    }

    @Override
    public Page findPage(Page<AdminUser> adminUserPage, AdminUser adminUser, Map map) {
        adminUser.setPage(adminUserPage);
        map.put("page",adminUserPage);
        adminUserPage.setContent(findAll(map));
        return adminUserPage;
    }

    public List<AdminUser> findAll(Map map) {
        return  adminDao.findList(map);
    }
}
