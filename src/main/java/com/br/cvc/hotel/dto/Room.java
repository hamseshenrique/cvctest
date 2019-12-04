package com.br.cvc.hotel.dto;

import java.io.Serializable;

public class Room implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer roomID;
	private String categoryName;
	private Double totalPrice;
	private Price price;
	
	public Room() {}

	public Integer getRoomID() {
		return roomID;
	}

	public void setRoomID(Integer roomID) {
		this.roomID = roomID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}	

}
