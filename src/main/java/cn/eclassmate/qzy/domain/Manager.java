package cn.eclassmate.qzy.domain;

import java.util.Date;

/**
 * 米谷管理员
 * Created by jiacy on 2017-07-24.
 */
public class Manager {

    private Integer id;
    private String name;
    private String password;
    private Date addTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
