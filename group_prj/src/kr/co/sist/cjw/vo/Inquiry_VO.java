package kr.co.sist.cjw.vo;

import java.sql.Date;

public class Inquiry_VO {
	private int inq_Id; 
	private String inq_Title;
	private String inq_Contents;
	private Date inq_Reg_Date;
	private String inq_Reply;
	private Date inq_Reply_Date;
	private String inq_Status;
	private int mem_Num;
	
	
	public Inquiry_VO() {
	}//Inquiry_VO


	public Inquiry_VO(int inq_Id, String inq_Titile, String inq_Contents, Date inq_Reg_Date, String inq_Reply,
			Date inq_Reply_Date, String inq_Status,int mem_Num) {
		this.inq_Id = inq_Id;
		this.inq_Title = inq_Titile;
		this.inq_Contents = inq_Contents;
		this.inq_Reg_Date = inq_Reg_Date;
		this.inq_Reply = inq_Reply;
		this.inq_Reply_Date = inq_Reply_Date;
		this.inq_Status=inq_Status;
		this.mem_Num = mem_Num;
	}//Inquiry_VO


	public int getInq_Id() {
		return inq_Id;
	}


	public void setInq_Id(int inq_Id) {
		this.inq_Id = inq_Id;
	}


	public String getInq_Title() {
		return inq_Title;
	}


	public void setInq_Title(String inq_Title) {
		this.inq_Title = inq_Title;
	}


	public String getInq_Contents() {
		return inq_Contents;
	}


	public void setInq_Contents(String inq_Contents) {
		this.inq_Contents = inq_Contents;
	}


	public Date getInq_Reg_Date() {
		return inq_Reg_Date;
	}


	public void setInq_Reg_Date(Date inq_Reg_Date) {
		this.inq_Reg_Date = inq_Reg_Date;
	}


	public String getInq_Reply() {
		return inq_Reply;
	}




	public void setInq_Reply_Date(Date inq_Reply_Date) {
		this.inq_Reply_Date = inq_Reply_Date;
	}


	public int getMem_Num() {
		return mem_Num;
	}


	public void setMem_Num(int mem_Num) {
		this.mem_Num = mem_Num;
	}
	
	


	public String getInq_Status() {
		return inq_Status;
	}


	public void setInq_Status(String inq_Status) {
		this.inq_Status = inq_Status;
	}


	public Date getInq_Reply_Date() {
		return inq_Reply_Date;
	}


	@Override
	public String toString() {
		return "Inquiry_VO [inq_Id=" + inq_Id + ", inq_Title=" + inq_Title + ", inq_Contents=" + inq_Contents
				+ ", inq_Reg_Date=" + inq_Reg_Date + ", inq_Reply=" + inq_Reply + ", inq_Reply_Date=" + inq_Reply_Date
				+ ", inq_Status=" + inq_Status + ", mem_Num=" + mem_Num + "]";
	}


	
	

}//class
