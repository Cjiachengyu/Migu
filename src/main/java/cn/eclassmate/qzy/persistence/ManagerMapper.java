package cn.eclassmate.qzy.persistence;

import cn.eclassmate.qzy.domain.Manager;

/**
 * Created by jiacy on 2017-07-24.
 */
public interface ManagerMapper {

    int insertManager(Manager manager);

    int updateManager(Manager manager);

    Manager getManagerByName(String name);

}
