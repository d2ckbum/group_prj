package kr.co.sist.kji;

public class MfgVO {

	private int mfgNum;
	private String mfgName;
	
	public MfgVO() {
	}

	public MfgVO(int mfgNum, String mfgName) {
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
