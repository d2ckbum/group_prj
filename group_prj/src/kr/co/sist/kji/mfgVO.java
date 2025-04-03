package kr.co.sist.kji;

public class mfgVO {

	private int mfgNum;
	private String mfgName;
	
	public mfgVO() {
	}

	public mfgVO(int mfgNum, String mfgName) {
		super();
		this.mfgNum = mfgNum;
		this.mfgName = mfgName;
	}

	public int getMfgNum() {
		return mfgNum;
	}

	public void setMfgNum(int mfgNum) {
		this.mfgNum = mfgNum;
	}

	public String getMfgName() {
		return mfgName;
	}

	public void setMfgName(String mfgName) {
		this.mfgName = mfgName;
	}

	@Override
	public String toString() {
		return "mfgVO [mfgNum=" + mfgNum + ", mfgName=" + mfgName + "]";
	}
	
}//class
