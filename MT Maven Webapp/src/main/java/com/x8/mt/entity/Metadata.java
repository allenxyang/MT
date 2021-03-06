package com.x8.mt.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * 作者:GodDispose
 * 时间:下午8:40:22
 * 作用:
 * 参数:
 */
public class Metadata {
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间
	
	private int ID;
	private String NAME;
	private int COLLECTJOBID;
	private String DESCRIPTION;//可以缺省
	private int METAMODELID;
	private Date CREATETIME;
	private Date UPDATETIME;
	private String CHECKSTATUS;
	private String ATTRIBUTES;
	private int VERSION;

	public Metadata(){}

	public Metadata( String name, int collectJobId,
			String description, int metaModelId, Date createTime,
			Date updateTime, String checkStatus, String attributes,int version) {
		super();
		this.NAME = name;
		this.COLLECTJOBID = collectJobId;
		this.DESCRIPTION = description;
		this.METAMODELID = metaModelId;
		this.CREATETIME = createTime;
		this.UPDATETIME = updateTime;
		this.CHECKSTATUS = checkStatus;
		this.ATTRIBUTES = attributes;
		this.VERSION = version;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public int getCOLLECTJOBID() {
		return COLLECTJOBID;
	}

	public void setCOLLECTJOBID(int cOLLECTJOBID) {
		COLLECTJOBID = cOLLECTJOBID;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}

	public int getMETAMODELID() {
		return METAMODELID;
	}

	public void setMETAMODELID(int mETAMODELID) {
		METAMODELID = mETAMODELID;
	}

	public String getCREATETIME() {
		String str=sdf.format(CREATETIME); 
		return str;
	}

	public void setCREATETIME(Date cREATETIME) {
		CREATETIME = cREATETIME;
	}

	public String getUPDATETIME() {
		String str=sdf.format(UPDATETIME); 
		return str;
	}

	public void setUPDATETIME(Date uPDATETIME) {
		UPDATETIME = uPDATETIME;
	}

	public String getCHECKSTATUS() {
		return CHECKSTATUS;
	}

	public void setCHECKSTATUS(String cHECKSTATUS) {
		CHECKSTATUS = cHECKSTATUS;
	}

	public String getATTRIBUTES() {
		return ATTRIBUTES;
	}

	public void setATTRIBUTES(String aTTRIBUTES) {
		ATTRIBUTES = aTTRIBUTES;
	}

	public int getVERSION() {
		return VERSION;
	}

	public void setVERSION(int vERSION) {
		VERSION = vERSION;
	}

}
