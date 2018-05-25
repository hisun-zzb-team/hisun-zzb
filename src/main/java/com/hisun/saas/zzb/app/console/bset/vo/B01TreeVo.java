package com.hisun.saas.zzb.app.console.bset.vo;

import java.io.Serializable;


public class B01TreeVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6144477656746707097L;

	/**
	 * 树节点id
	 */
	private String id;
	
	/**
	 * 树节点父id
	 */
	private String pId;
	
	/**
	 * 树节点名字
	 */
	private String name;

	/**
	 * 类型
	 */
	private String dataType;
	
	/**
	 * 是否打开节点，默认不打开
	 */
	private boolean open = false;

	/**
	 * 资源对应的url
	 */
	private String href;
	
	/**
	 * 是否可拖拽，默认拖拽
	 */
	private boolean drag = true;
	
	/**
	 * 是否可成为父节点，默认不能
	 */
	private boolean dropInner = false;
	
	/**
	 * 是否可成为根节点，默认不能
	 */
	private boolean dropRoot = false;
	
	/**
	 * 是否显示删除按钮，默认不显示
	 */
	private boolean noRemoveBtn = true;
	
	/**
	 * 是否显示修改按钮，默认不显示
	 */
	private boolean noEditBtn = true;
	
	/**
	 * 是否不允许右键，默认不允许
	 */
	private boolean noR = true;
	
	/**
	 * 是否为父节点
	 */
	private boolean isParent = false;
	
	/**
	 * 排序字段
	 */
	private int priority;

	private String icon;
	
	private String iconSkin;

	
	/**
	 * 根节点
	 * @return
	 */
//	public B01TreeVo oneRoot(){
//		B01TreeVo vo = new B01TreeVo();
//		vo.setId("1");
//		vo.setName("机构");
//		vo.setpId("0");
//		vo.setIsParent(true);
//		vo.setDropInner(true);
//		vo.setOpen(true);
//		return vo;
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public boolean isDrag() {
		return drag;
	}

	public void setDrag(boolean drag) {
		this.drag = drag;
	}

	public boolean isDropInner() {
		return dropInner;
	}

	public void setDropInner(boolean dropInner) {
		this.dropInner = dropInner;
	}

	public boolean isDropRoot() {
		return dropRoot;
	}

	public void setDropRoot(boolean dropRoot) {
		this.dropRoot = dropRoot;
	}

	public boolean isNoRemoveBtn() {
		return noRemoveBtn;
	}

	public void setNoRemoveBtn(boolean noRemoveBtn) {
		this.noRemoveBtn = noRemoveBtn;
	}

	public boolean isNoEditBtn() {
		return noEditBtn;
	}

	public void setNoEditBtn(boolean noEditBtn) {
		this.noEditBtn = noEditBtn;
	}

	public boolean isNoR() {
		return noR;
	}

	public void setNoR(boolean noR) {
		this.noR = noR;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconSkin() {
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
}
