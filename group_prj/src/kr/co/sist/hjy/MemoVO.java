package kr.co.sist.hjy;

public class MemoVO  {
	private String fixNum;
	private StringBuilder strFixMemo;
	private int fixMemoSize;
	

	
	public MemoVO(String fixNum, StringBuilder strFixMemo, int fixMemoSize) {
		super();
		this.fixNum = fixNum;
		this.strFixMemo = strFixMemo;
		this.fixMemoSize = fixMemoSize;
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
	public int getFixMemoSize() {
		return fixMemoSize;
	}
	public void setFixMemoSize(int fixMemoSize) {
		this.fixMemoSize = fixMemoSize;
	}

	
}//class
