package com.qf.entity;

import java.io.Serializable;

/**
@author: WangXi
@Date: 2019/6/11
*/
public class TProductType implements Serializable {
    private Long id;

    private Long pid;

    private String name;

    /**
    * 0:????
            1?????
    */
    private Boolean flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}