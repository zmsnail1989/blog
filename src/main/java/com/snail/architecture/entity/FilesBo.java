package com.snail.architecture.entity;

import java.io.Serializable;
import java.util.Date;

public class FilesBo extends Pager implements Serializable {

	private static final long serialVersionUID = -4424161748464987533L;
	private String id;
	private String suffix;
	private String name;
	private String real_url;
	private String url;
	private Date update;
	private String up_user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUp_user() {
		return up_user;
	}

	public void setUp_user(String up_user) {
		this.up_user = up_user;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	public String getReal_url() {
		return real_url;
	}

	public void setReal_url(String real_url) {
		this.real_url = real_url;
	}

}
