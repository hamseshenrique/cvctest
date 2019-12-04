package com.br.cvc.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.cvc.hotel.dto.ResponseHotels;
import com.br.cvc.hotel.service.HotelService;

@RestController
@RequestMapping("hotels")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@GetMapping("/avail")
	public ResponseHotels avail(@RequestParam("cityCode") Integer cityCode,
			@RequestParam("checkin") String checkin,@RequestParam("checkout") String checkout,
			@RequestParam("qtdAdult") Integer qtdAdult,@RequestParam("qtdChild") Integer qtdChild) {
		
		final ResponseHotels responseHotels = new ResponseHotels();
		responseHotels.setStatus(HttpStatus.OK.value());
		
		try {
			responseHotels.setHotels(hotelService.avail(cityCode, checkin,
					checkout, qtdAdult, qtdChild));
		}catch (Exception e) {
			e.printStackTrace();
			responseHotels.setMsg(e.getMessage());
			responseHotels.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		
		return responseHotels;
	}
	@GetMapping("/hotel")
	public ResponseHotels hotel(@RequestParam("idHotel") Integer idHotel,
			@RequestParam("checkin") String checkin,@RequestParam("checkout") String checkout,
			@RequestParam("qtdAdult") Integer qtdAdult,@RequestParam("qtdChild") Integer qtdChild) {
		
		final ResponseHotels responseHotels = new ResponseHotels();
		responseHotels.setStatus(HttpStatus.OK.value());
		
		try {
			responseHotels.setHotels(hotelService.hotel(idHotel, checkin,
					checkout, qtdAdult, qtdChild));
		}catch (Exception e) {
			e.printStackTrace();
			responseHotels.setMsg(e.getMessage());
			responseHotels.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		
		return responseHotels;
	}

}
