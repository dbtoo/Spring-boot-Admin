package com.shenma.model;

import java.sql.Date;

public class Adsense  extends BaseObject  {
String[] string=new String[]{"PC","APP"};

    private long id;//id
    private int type;//类型:PC，APP
    private int place;//位置:首页，左侧，右侧，列表页
    private Date addDate;//创建时间
    private Date updateDate;//修改时间
    private int state;//状态 1 正常 2 删除

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
