package com.shenma.model;

import java.sql.Date;

public class Advert extends BaseObject {

	private long id;//id
	private long adsense_id;//广告位id
	private String url;//链接
	private String image;//封面图片
	private String alt;//简介
	private String remark;//备注
	private String sort;//排序
	private Date addDate;//添加时间
	private Date updateDate;//修改时间
	private int state;//状态

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAdsense_id() {
		return adsense_id;
	}

	public void setAdsense_id(long adsense_id) {
		this.adsense_id = adsense_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
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
