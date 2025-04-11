package kr.co.sist.kji;

public class MemberDataDTO {
	private MemberVO mVO = new MemberVO();;
	private String carType = "";
	private String mfgName = "";
	public MemberDataDTO() {
		super();
	}
	public MemberDataDTO(MemberVO mVO, String carType, String mfgName) {
		super();
		this.mVO = mVO;
		this.carType = carType;
		this.mfgName = mfgName;
	}
	public MemberVO getmVO() {
		return mVO;
	}
	public void setmVO(MemberVO mVO) {
		this.mVO = mVO;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getMfgName() {
		return mfgName;
	}
	public void setMfgName(String mfgName) {
		this.mfgName = mfgName;
	}
	@Override
	public String toString() {
		return "MemberDataDTO " + mVO.toString()+"[carType=" + carType + ", mfgName=" + mfgName + "]";
	}
	
	
}
