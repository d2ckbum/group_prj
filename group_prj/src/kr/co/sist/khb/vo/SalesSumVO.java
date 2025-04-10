package kr.co.sist.khb.vo;

public class SalesSumVO {
	
	private int itemNum;
    private String itemName;
    private int quantity;			//수량
    private int realTotal;   //순수입
    private int total;  // 총수입
	
    
    public SalesSumVO() {
    	
	}


	public SalesSumVO(int itemNum, String itemName, int quantity, int realTotal, int total) {
		super();
		this.itemNum = itemNum;
		this.itemName = itemName;
		this.quantity = quantity;
		this.realTotal = realTotal;
		this.total = total;
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


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getRealTotal() {
		return realTotal;
	}


	public void setRealTotal(int realTotal) {
		this.realTotal = realTotal;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	@Override
	public String toString() {
		return "SalesSumVO [itemNum=" + itemNum + ", itemName=" + itemName + ", quantity=" + quantity
				+ ", realTotal=" + realTotal + ", total=" + total + "]";
	}
    
    
    
    
    
}
