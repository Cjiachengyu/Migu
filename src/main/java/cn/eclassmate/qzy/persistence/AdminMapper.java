package cn.eclassmate.qzy.persistence;

import cn.eclassmate.qzy.domain.Admin;

/**
 * Created by jiacy on 2017-07-24.
 */
public interface AdminMapper {

    int insertAdmin(Admin manager);

    int updateAdmin(Admin manager);

    Admin getAdminByName(String name);

}
