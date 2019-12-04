package com.br.cvc.hotel.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.br.cvc.hotel.dto.Hotel;
import com.br.cvc.hotel.enums.BrokerEnum;
import com.google.gson.Gson;

@Service
public class BrokerServiceImpl implements BrokerService {
	
	private final RestTemplate restTemplate = new RestTemplate();
	private final Gson gson = new Gson();

	@Override
	public List<Hotel> request(final Integer code,final BrokerEnum brokerEnum) throws Exception{
		
		final ResponseEntity<String> response = restTemplate.getForEntity(brokerEnum.getRequest()+code, String.class);
		final List<Hotel> hotels = new ArrayList<Hotel>();
		
		if(response.getStatusCode().equals(HttpStatus.OK)) {			
			hotels.addAll(Arrays.asList(gson.fromJson(response.getBody(),Hotel[].class)));
		}else {
			throw new Exception("Erro ao pesquisar hotel");
		}
		
		return hotels;
	}

}
