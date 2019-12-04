package com.br.cvc.hotel.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import com.br.cvc.hotel.dto.Hotel;
import com.br.cvc.hotel.dto.Price;
import com.br.cvc.hotel.dto.Room;

public class CalcComissao implements Callable<Hotel>{
	
	private Hotel hotel;
	private Integer qtdDay;
	private final Double COMISSAO = 0.7; 
	
	public CalcComissao(final Hotel hotel,final Integer qtdDay) {
		this.hotel = hotel;
		this.qtdDay = qtdDay;
	}

	@Override
	public Hotel call() throws Exception {
		final Hotel hotelComissao = new Hotel();
		hotelComissao.setCityCode(hotel.getCityCode());
		hotelComissao.setCityName(hotel.getCityName());
		hotelComissao.setId(hotel.getId());
		hotelComissao.setName(hotel.getName());
		hotelComissao.setRooms(new ArrayList<Room>());
		
		if(hotel.getRooms() != null && !hotel.getRooms().isEmpty()) {
			
			for(Room room : hotel.getRooms()) {
				Double adult = 0.0;
				Double child = 0.0;
								
				adult = comissao(room.getPrice().getAdult(),qtdDay);
				child = comissao(room.getPrice().getChild(),qtdDay);
				final Room roomCom = new Room();
				roomCom.setCategoryName(room.getCategoryName());
				roomCom.setRoomID(room.getRoomID());
				roomCom.setTotalPrice(roundDecimal(adult+child));
				
				final Price price = new Price();
				price.setAdult(room.getPrice().getAdult());
				price.setChild(room.getPrice().getChild());
				
				roomCom.setPrice(price);
				hotelComissao.getRooms().add(roomCom);
			}
		}
		
		return hotelComissao;
	}
	
	private Double comissao(final Double price,final Integer qtdDay) {
		Double comissaoPrice = 0.0;
		if(price != null && price.doubleValue() > 0.0) {
			comissaoPrice = ((price*qtdDay)/COMISSAO); 
		}
		
		return roundDecimal(comissaoPrice);
	}
	
	private Double roundDecimal(final Double price) {
		return new BigDecimal(price).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
	}	

}
