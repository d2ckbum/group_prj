package kr.co.sist.khb.vo;

public class OrderVO {

    private int itemNum; // 선택한 아이템 번호 (item 테이블의 item_num)
    private String itemName; // 선택한 아이템 이름 (item 테이블의 item_name)
    private int itemPrice; // 선택한 아이템 가격 (item 테이블의 item_price)
    private int itemCost; // 선택한 아이템의 원가 
    private int itemRepairCost; // 선택한 아이템 수리 비용 (item 테이블의 item_repair_cost)
    private int totalPrice; // 총 결제 금액 (itemPrice + itemRepairCost)
    
    private int memNum; // 현재 로그인한 회원 번호
    private int carNum;
    
    
	public OrderVO(int itemNum, String itemName, int itemPrice, int itemCost, int itemRepairCost, int totalPrice, int memNum) {

	}


	public OrderVO(int itemNum, String itemName, int itemPrice, int itemCost, int itemRepairCost, int totalPrice, int memNum,
			int carNum) {
		super();
		this.itemNum = itemNum;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemCost = itemCost;
		this.itemRepairCost = itemRepairCost;
		this.totalPrice = totalPrice;
		this.memNum = memNum;
		this.carNum = carNum;
	}


	@Override
	public String toString() {
		return "OrderVO [itemNum=" + itemNum + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemCost="
				+ itemCost + ", itemRepairCost=" + itemRepairCost + ", totalPrice=" + totalPrice + ", memNum=" + memNum
				+ ", carNum=" + carNum + "]";
	}


	public int getItemNum() {
		return itemNum;
	}


	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public int getItemPrice() {
		return itemPrice;
	}


	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}


	public int getItemCost() {
		return itemCost;
	}


	public void setItemCost(int itemCost) {
		this.itemCost = itemCost;
	}


	public int getItemRepairCost() {
		return itemRepairCost;
	}


	public void setItemRepairCost(int itemRepairCost) {
		this.itemRepairCost = itemRepairCost;
	}


	public int getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}


	public int getMemNum() {
		return memNum;
	}


	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}


	public int getCarNum() {
		return carNum;
	}


	public void setCarNum(int carNum) {
		this.carNum = carNum;
	}
	
	


	
}