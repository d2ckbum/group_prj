package kr.co.sist.hjy;

public class MemoVO  {
	private String fixNum;
	private StringBuilder strFixMemo;

	
	public MemoVO(String fixNum, StringBuilder strFixMemo) {
		
		this.fixNum = fixNum;
		this.strFixMemo = strFixMemo;
	}




	public String getFixNum() {
		return fixNum;
	}




	public void setFixNum(String fixNum) {
		this.fixNum = fixNum;
	}




	public StringBuilder getStrFixMemo() {
		return strFixMemo;
	}


	public void setStrFixMemo(StringBuilder strFixMemo) {
		this.strFixMemo = strFixMemo;
	}
}//class
