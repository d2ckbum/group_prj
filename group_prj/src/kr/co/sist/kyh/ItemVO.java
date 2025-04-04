package kr.co.sist.kyh;

import java.util.Date;

public class ItemVO {
	
    private int itemNum; // 상품 번호
    private String itemName; // 상품명
    private int itemStock; // 재고 수량
    private int itemCost; // 상품 원가
    private int itemPrice; // 판매 가격
    private int itemRepairCost; // 공임비 가격
    private Date itemRegDate; // 등록일
    private int carNum; // 차량식별번호
    
	public ItemVO() {
		
	}

	public ItemVO(int itemNum, String itemName, int itemStock, int itemCost, int itemPrice, int itemRepairCost,
			Date itemRegDate, int carNum) {
		
		this.itemNum = itemNum;
		this.itemName = itemName;
		this.itemStock = itemStock;
		this.itemCost = itemCost;
		this.itemPrice = itemPrice;
		this.itemRepairCost = itemRepairCost;
		this.itemRegDate = itemRegDate;
		this.carNum = carNum;
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

	public int getItemStock() {
		return itemStock;
	}

	public void setItemStock(int itemStock) {
		this.itemStock = itemStock;
	}

	public int getItemCost() {
		return itemCost;
	}

	public void setItemCost(int itemCost) {
		this.itemCost = itemCost;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getItemRepairCost() {
		return itemRepairCost;
	}

	public void setItemRepairCost(int itemRepairCost) {
		this.itemRepairCost = itemRepairCost;
	}

	public Date getItemRegDate() {
		return itemRegDate;
	}

	public void setItemRegDate(Date itemRegDate) {
		this.itemRegDate = itemRegDate;
	}

	public int getCarNum() {
		return carNum;
	}

	public void setCarNum(int carNum) {
		this.carNum = carNum;
	}

	@Override
	public String toString() {
		return "ItemVO [itemNum=" + itemNum + ", itemName=" + itemName + ", itemStock=" + itemStock + ", itemCost="
				+ itemCost + ", itemPrice=" + itemPrice + ", itemRepairCost=" + itemRepairCost + ", itemRegDate="
				+ itemRegDate + ", carNum=" + carNum + "]";
	}

}
