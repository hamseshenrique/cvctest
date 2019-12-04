package com.br.cvc.hotel.enums;

public enum BrokerEnum {
	
	AVAIL("https://cvcbackendhotel.herokuapp.com/hotels/avail/"),
	HOTEL("https://cvcbackendhotel.herokuapp.com/hotels/");
	
	private String request;
	
	private BrokerEnum(final String request) {
		this.request = request;		
	}

	public String getRequest() {
		return request;
	}
}
