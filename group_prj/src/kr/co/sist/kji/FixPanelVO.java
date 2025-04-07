package kr.co.sist.kji;

import java.sql.Clob;
import java.sql.Timestamp;

public class FixPanelVO {
	private String fixNum;
	private Timestamp fixRegDate;
	private String itemName;
	private String mfgName;
	private String carType;
	private int memNum;
	private String memName;
	private int total;//결제금액
	private String fixStatus;
	
	private String memTell;
	private Clob fixMemo;
	
	public FixPanelVO() {
		super();
	}


	public FixPanelVO(String fixNum, Timestamp fixRegDate, Clob fixMemo, String itemName, String mfgName, String carType, int memNum,
			String memName, int total, String fixStatus, String memTell) {
		super();
		this.fixNum = fixNum;
		this.fixRegDate = fixRegDate;
		this.itemName = itemName;
		this.mfgName = mfgName;
		this.carType = carType;
		this.memNum = memNum;
		this.memName = memName;
		this.total = total;
		this.fixStatus = fixStatus;
		this.memTell = memTell;
		this.fixMemo = fixMemo;
	}

	
	public String getFixNum() {
		return fixNum;
	}
	public void setFixNum(String fixNum) {
		this.fixNum = fixNum;
	}
	public Timestamp getFixRegDate() {
		return fixRegDate;
	}
	public void setFixRegDate(Timestamp fixRegDate) {
		this.fixRegDate = fixRegDate;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getMfgName() {
		return mfgName;
	}
	public void setMfgName(String mfgName) {
		this.mfgName = mfgName;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public int getMemNum() {
		return memNum;
	}
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getFixStatus() {
		return fixStatus;
	}
	public void setFixStatus(String fixStatus) {
		this.fixStatus = fixStatus;
	}
	public String getMemTell() {
		return memTell;
	}
	public void setMemTell(String memTell) {
		this.memTell = memTell;
	}
	public Clob getFixMemo() {
		return fixMemo;
	}
	public void setFixMemo(Clob fixMemo) {
		this.fixMemo = fixMemo;
	}
	
	
	@Override
	public String toString() {
		return "FixPanelVO [fixNum=" + fixNum + ", fixRegDate=" + fixRegDate + ", itemName=" + itemName + ", mfgName="
				+ mfgName + ", carType=" + carType + ", memNum=" + memNum + ", memName=" + memName + ", total=" + total
				+ ", fixStatus=" + fixStatus + ", memTell=" + memTell + ", fixMemo=" + fixMemo + "]";
	}
	





}//class
