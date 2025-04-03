package kr.co.sist.kji;

import java.sql.Date;

public class MemberVO {
	private int memNum;
	private String memId;
	private String memPass;
	private String memName;
	private String memEmail;
	private String memTell;
	private String memZipcode;
	private String memAddr1;
	private String memAddr2;
	private Date memRegDate;
	private String memFlag;
	private int carNum;
	private int mfgNum;
	
	public MemberVO() {
	}
	public MemberVO(int memNum, String memId, String memPass, String memName, String memEmail, String memTell,
			String memZipcode, String memAddr1, String memAddr2, Date memRegDate, String memFlag, int carNum,
			int mfgNum) {
		this.memNum = memNum;
		this.memId = memId;
		this.memPass = memPass;
		this.memName = memName;
		this.memEmail = memEmail;
		this.memTell = memTell;
		this.memZipcode = memZipcode;
		this.memAddr1 = memAddr1;
		this.memAddr2 = memAddr2;
		this.memRegDate = memRegDate;
		this.memFlag = memFlag;
		this.carNum = carNum;
		this.mfgNum = mfgNum;
	}
	public int getMemNum() {
		return memNum;
	}
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPass() {
		return memPass;
	}
	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemTell() {
		return memTell;
	}
	public void setMemTell(String memTell) {
		this.memTell = memTell;
	}
	public String getMemZipcode() {
		return memZipcode;
	}
	public void setMemZipcode(String memZipcode) {
		this.memZipcode = memZipcode;
	}
	public String getMemAddr1() {
		return memAddr1;
	}
	public void setMemAddr1(String memAddr1) {
		this.memAddr1 = memAddr1;
	}
	public String getMemAddr2() {
		return memAddr2;
	}
	public void setMemAddr2(String memAddr2) {
		this.memAddr2 = memAddr2;
	}
	public Date getMemRegDate() {
		return memRegDate;
	}
	public void setMemRegDate(Date memRegDate) {
		this.memRegDate = memRegDate;
	}
	public String getMemFlag() {
		return memFlag;
	}
	public void setMemFlag(String memFlag) {
		this.memFlag = memFlag;
	}
	public int getCarNum() {
		return carNum;
	}
	public void setCarNum(int carNum) {
		this.carNum = carNum;
	}
	public int getMfgNum() {
		return mfgNum;
	}
	public void setMfgNum(int mfgNum) {
		this.mfgNum = mfgNum;
	}
	@Override
	public String toString() {
		return "MemberVO [memNum=" + memNum + ", memId=" + memId + ", memPass=" + memPass + ", memName=" + memName
				+ ", memEmail=" + memEmail + ", memTell=" + memTell + ", memZipcode=" + memZipcode + ", memAddr1="
				+ memAddr1 + ", memAddr2=" + memAddr2 + ", memRegDate=" + memRegDate + ", memFlag=" + memFlag
				+ ", carNum=" + carNum + ", mfgNum=" + mfgNum + "]";
	}
	
	
	
}//class
