package com.uplooking.car_renting;

public class Car {
	private String no;
	private String brand;
	private String type;
	private String color;
	private String pricePerDay;
	private boolean isAvailale;
	
	public Car() {
		
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(String pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public boolean isAvailale() {
		return isAvailale;
	}

	public void setAvailale(boolean isAvailale) {
		this.isAvailale = isAvailale;
	}
	
	public boolean add() {
		return true;
	}
	
	public boolean rent() {
		return true;
	}
	
	public boolean back() {
		return true;
	}
}
