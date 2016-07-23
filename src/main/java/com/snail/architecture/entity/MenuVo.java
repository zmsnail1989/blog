/**   
* @Title: MenuVo.java 
* @Package com.snail.architecture.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhangmin
* @date 2016年3月8日 下午10:12:49 
* @version V1.0   
*/
package com.snail.architecture.entity;

import java.io.Serializable;
import java.util.List;

/** 
 * @ClassName: MenuVo 
 * @Description: TODO(菜单属性类) 
 * @author zhangmin
 * @date 2016年3月8日 下午10:12:49 
 *  
 */
public class MenuVo implements Serializable{
	private static final long serialVersionUID = 2061254081994415921L;
	private Long id;   //资源id
	private String name; //资源名称
	private String url; //资源路径
	private Long parentId;//父级id
	private List<MenuVo> submenu;
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<MenuVo> getSubmenu() {
		return submenu;
	}
	public void setSubmenu(List<MenuVo> submenu) {
		this.submenu = submenu;
	}
	
}
