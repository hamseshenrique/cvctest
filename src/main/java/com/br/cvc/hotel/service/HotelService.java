package com.br.cvc.hotel.service;

import java.util.List;

import com.br.cvc.hotel.dto.Hotel;

public interface HotelService {
	
	List<Hotel> avail(final Integer cityCode,final String checkin,final String checkout,
			final Integer qtdAdult,final Integer qtdChild)throws Exception;
	
	List<Hotel> hotel(final Integer idHotel,final String checkin,final String checkout,
			final Integer qtdAdult,final Integer qtdChild)throws Exception;
	

}
