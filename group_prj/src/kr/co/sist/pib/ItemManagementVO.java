package kr.co.sist.pib;

import java.sql.Date;

public class ItemManagementVO {

private int item_num;
private String item_name;
private int item_stock;
private int item_cost;
private int item_price;
private int item_repair_cost;
private Date item_reg_date;
private String car_type;

public ItemManagementVO() {
	
}



public ItemManagementVO(int item_num, String item_name, int item_stock, int item_cost, int item_price,
		int item_repair_cost, Date item_reg_date, String car_type) {
	super();
	this.item_num = item_num;
	this.item_name = item_name;
	this.item_stock = item_stock;
	this.item_cost = item_cost;
	this.item_price = item_price;
	this.item_repair_cost = item_repair_cost;
	this.item_reg_date = item_reg_date;
	this.car_type = car_type;
}




public int getItem_repair_cost() {
	return item_repair_cost;
}


public void setItem_repair_cost(int item_repair_cost) {
	this.item_repair_cost = item_repair_cost;
}




public int getItem_num() {
	return item_num;
}


public void setItem_num(int item_num) {
	this.item_num = item_num;
}


public String getItem_name() {
	return item_name;
}


public void setItem_name(String item_name) {
	this.item_name = item_name;
}


public int getItem_stock() {
	return item_stock;
}


public void setItem_stock(int item_stock) {
	this.item_stock = item_stock;
}


public int getItem_cost() {
	return item_cost;
}


public void setItem_cost(int item_cost) {
	this.item_cost = item_cost;
}


public Date getItem_reg_date() {
	return item_reg_date;
}


public void setItem_reg_date(Date item_reg_date) {
	this.item_reg_date = item_reg_date;
}


public String getCar_type() {
	return car_type;
}


public void setCar_type(String car_type) {
	this.car_type = car_type;
}




public int getItem_price() {
	return item_price;
}


public void setItem_price(int item_price) {
	this.item_price = item_price;
}



@Override
public String toString() {
	return "ItemManagementVO [item_num=" + item_num + ", item_name=" + item_name + ", item_stock=" + item_stock
			+ ", item_cost=" + item_cost + ", item_price=" + item_price + ", item_repair_cost=" + item_repair_cost
			+ ", item_reg_date=" + item_reg_date + ", car_type=" + car_type + "]";
}









	
	
}
