package kr.co.sist.hjy;

import java.sql.Clob;
import java.sql.Date;

public class FixPanelVO {
	private int fixNum;
	private Date fixRegDate;
	private String itemName;
	private String mfgName;
	private String carType;
	private String memName;
	//결제금액이 추가되어야 한다.
	private String fixStatus;
	
	
	public FixPanelVO(int fixNum, Date fixRegDate, String itemName, String mfgName, String carType, String memName,
			String fixStatus) {
		super();
		this.fixNum = fixNum;
		this.fixRegDate = fixRegDate;
		this.itemName = itemName;
		this.mfgName = mfgName;
		this.carType = carType;
		this.memName = memName;
		this.fixStatus = fixStatus;
	}


	public int getFixNum() {
		return fixNum;
	}


	public void setFixNum(int fixNum) {
		this.fixNum = fixNum;
	}


	public Date getFixRegDate() {
		return fixRegDate;
	}


	public void setFixRegDate(Date fixRegDate) {
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


	public String getMemName() {
		return memName;
	}


	public void setMemName(String memName) {
		this.memName = memName;
	}


	public String getFixStatus() {
		return fixStatus;
	}


	public void setFixStatus(String fixStatus) {
		this.fixStatus = fixStatus;
	}


	@Override
	public String toString() {
		return "FixPanelVO [fixNum=" + fixNum + ", fixRegDate=" + fixRegDate + ", itemName=" + itemName + ", mfgName="
				+ mfgName + ", carType=" + carType + ", memName=" + memName + ", fixStatus=" + fixStatus + "]";
	}
	

}//class
