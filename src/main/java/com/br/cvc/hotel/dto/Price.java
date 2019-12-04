package com.br.cvc.hotel.dto;

import java.io.Serializable;

public class Price implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private Double adult;
	private Double child;
	
	public Price() {}

	public Double getAdult() {
		return adult;
	}

	public void setAdult(Double adult) {
		this.adult = adult;
	}

	public Double getChild() {
		return child;
	}

	public void setChild(Double child) {
		this.child = child;
	}
	
}