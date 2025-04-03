package kr.co.sist.cjw.vo;

import java.sql.Date;

public class Inquiry_VO {
	private int inq_id; 
	private String inq_titile;
	private String inq_contents;
	private Date inq_date;
	private String inq_reply;
	private Date inq_reply_date;
	private int mem_num;
	
	
	public Inquiry_VO() {
	}//Inquiry_VO


	public Inquiry_VO(int inq_id, String inq_titile, String inq_contents, Date inq_date, String inq_reply,
			Date inq_reply_date, int mem_num) {
		this.inq_id = inq_id;
		this.inq_titile = inq_titile;
		this.inq_contents = inq_contents;
		this.inq_date = inq_date;
		this.inq_reply = inq_reply;
		this.inq_reply_date = inq_reply_date;
		this.mem_num = mem_num;
	}//Inquiry_VO


	public int getInq_id() {
		return inq_id;
	}


	public void setInq_id(int inq_id) {
		this.inq_id = inq_id;
	}


	public String getInq_titile() {
		return inq_titile;
	}


	public void setInq_titile(String inq_titile) {
		this.inq_titile = inq_titile;
	}


	public String getInq_contents() {
		return inq_contents;
	}


	public void setInq_contents(String inq_contents) {
		this.inq_contents = inq_contents;
	}


	public Date getInq_date() {
		return inq_date;
	}


	public void setInq_date(Date inq_date) {
		this.inq_date = inq_date;
	}


	public String getInq_reply() {
		return inq_reply;
	}


	public void setInq_reply(String inq_reply) {
		this.inq_reply = inq_reply;
	}


	public Date getInq_reply_date() {
		return inq_reply_date;
	}


	public void setInq_reply_date(Date inq_reply_date) {
		this.inq_reply_date = inq_reply_date;
	}


	public int getMem_num() {
		return mem_num;
	}


	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}


	@Override
	public String toString() {
		return "Inquiry_VO [inq_id=" + inq_id + ", inq_titile=" + inq_titile + ", inq_contents=" + inq_contents
				+ ", inq_date=" + inq_date + ", inq_reply=" + inq_reply + ", inq_reply_date=" + inq_reply_date
				+ ", mem_num=" + mem_num + "]";
	}
	

}//class
