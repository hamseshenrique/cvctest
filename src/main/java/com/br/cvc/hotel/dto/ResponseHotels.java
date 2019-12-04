package com.br.cvc.hotel.dto;

import java.io.Serializable;
import java.util.List;

public class ResponseHotels implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer status;
	private String msg;
	private List<Hotel> hotels;
	
	public ResponseHotels() {}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}
}
