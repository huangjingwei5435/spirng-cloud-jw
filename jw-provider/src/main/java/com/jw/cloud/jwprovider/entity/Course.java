package com.jw.cloud.jwprovider.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "course")
public class Course {

    private Long id;
    private String name;
    private String desct;
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesct() {
        return desct;
    }

    public void setDesct(String desct) {
        this.desct = desct;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
