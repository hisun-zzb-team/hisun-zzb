package com.hisun.saas.zzb.app.console.bset.vo;

import java.io.Serializable;


public class ImportParameterVo {
	//IP地址，端口号、数据库名、用户名、密码
	private String importType;//gwyglxt从公务员管理系统(浙大网新) zzzhywpt从组织综合业务平台 gbglxt从干部管理系统
	private String ip;
	private String port;
	private String dbName;
	private String dbUser;
	private String dbPwd;



	public String getImportType() {
		return importType;
	}

	public void setImportType(String importType) {
		this.importType = importType;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPwd() {
		return dbPwd;
	}

	public void setDbPwd(String dbPwd) {
		this.dbPwd = dbPwd;
	}
}
