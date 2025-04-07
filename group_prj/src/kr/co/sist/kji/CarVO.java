package kr.co.sist.kji;

public class CarVO {
	private int carNum;
	private String carType;
	public CarVO() {
		super();
	}
	public CarVO(int carNum, String carType) {
		super();
		this.carNum = carNum;
		this.carType = carType;
	}
	public int getCarNum() {
		return carNum;
	}
	public void setCarNum(int carNum) {
		this.carNum = carNum;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	@Override
	public String toString() {
		return "carVO [carNum=" + carNum + ", carType=" + carType + "]";
	}
	
	
}//class
