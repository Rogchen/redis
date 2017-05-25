package com.rogchen.admin.dao;

import com.rogchen.admin.domain.AdminUser;
import com.rogchen.common.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * @Author Rogchen{cbs@xmyr.cn}
 * Created by chbs on 2017/4/6.
 * {tags}
 */
@MyBatisDao
public interface AdminDao {

    AdminUser getAdminUser(Long id);

    List<AdminUser> findList(Map map);
}
