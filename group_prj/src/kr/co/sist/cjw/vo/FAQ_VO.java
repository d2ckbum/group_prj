package kr.co.sist.cjw.vo;

import java.sql.Date;

public class FAQ_VO {
	
	private int faq_num;
	private String faq_title;
	private String faq_contents;
	private Date faq_reg_date;
	
	public FAQ_VO() {
	}//FAQ_VO

	public FAQ_VO(int faq_num, String faq_title, String faq_contents, Date faq_reg_date) {
		this.faq_num = faq_num;
		this.faq_title = faq_title;
		this.faq_contents = faq_contents;
		this.faq_reg_date = faq_reg_date;
	}//FAQ_VO

	public int getFaq_num() {
		return faq_num;
	}

	public void setFaq_num(int faq_num) {
		this.faq_num = faq_num;
	}

	public String getFaq_title() {
		return faq_title;
	}

	public void setFaq_title(String faq_title) {
		this.faq_title = faq_title;
	}

	public String getFaq_contents() {
		return faq_contents;
	}

	public void setFaq_contents(String faq_contents) {
		this.faq_contents = faq_contents;
	}

	public Date getFaq_reg_date() {
		return faq_reg_date;
	}

	public void setFaq_reg_date(Date faq_reg_date) {
		this.faq_reg_date = faq_reg_date;
	}

	@Override
	public String toString() {
		return "FAQ_VO [faq_num=" + faq_num + ", faq_title=" + faq_title + ", faq_contents=" + faq_contents
				+ ", faq_reg_date=" + faq_reg_date + "]";
	}
	

}//class
