package com.br.cvc.hotel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.cvc.hotel.dto.Hotel;
import com.br.cvc.hotel.enums.BrokerEnum;

@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private BrokerService brokerService;
	private final ExecutorService execService = Executors.newCachedThreadPool(); 
	private final DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("YYYY-MM-dd");

	@Override
	public List<Hotel> avail(final Integer cityCode, final String checkin,
			final String checkout, final Integer qtdAdult, final Integer qtdChild) throws Exception{
		
		final List<Hotel> hotels = brokerService.request(cityCode,BrokerEnum.AVAIL);
		final List<Hotel> hotelsComissao = new ArrayList<Hotel>();
		
		hotelsComissao.addAll(calcComissao(hotels, calcQtdDay(checkin,checkout)));
				
		return hotelsComissao;
	}
	
	@Override
	public List<Hotel> hotel(final Integer idHotel,final String checkin,
			final String checkout,final Integer qtdAdult,final Integer qtdChild) throws Exception {
		
		final List<Hotel> hotels =  brokerService.request(idHotel, BrokerEnum.HOTEL);
		final List<Hotel> hotelsComissao = new ArrayList<Hotel>();

		hotelsComissao.addAll(calcComissao(hotels,calcQtdDay(checkin,checkout)));
		
		return hotelsComissao;
	}
	
	private Integer calcQtdDay(final String checkin,final String checkout) throws Exception {
		
		
		DateTime dtCheckin = dateTimeFormat.parseDateTime(checkin);
		DateTime dtCheckout = dateTimeFormat.parseDateTime(checkout);
		final Integer qtdDays = Days.daysBetween(dtCheckin,dtCheckout).getDays();
		
		if(qtdDays <= 0) {
			throw new Exception("Periodo Checkin e CheckOut invalidos");
		}
		
		return qtdDays;
	}
	
	private List<Hotel> calcComissao(final List<Hotel> hotels,final Integer qtdDay)  throws Exception{
		final List<Hotel> hotelsComissao = new ArrayList<Hotel>();
		for (Hotel hotel : hotels) {
			final CalcComissao calcComissao = new CalcComissao(hotel, qtdDay);
			final Future<Hotel> future = execService.submit(calcComissao);
			while(!future.isDone());
			hotelsComissao.add(future.get());
		}
		
		return hotelsComissao;
	}
	

	public void setBrokerService(BrokerService brokerService) {
		this.brokerService = brokerService;
	}
	

}
