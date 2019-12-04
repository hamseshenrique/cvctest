package com.br.cvc.hotel.service;

import java.util.List;

import com.br.cvc.hotel.dto.Hotel;
import com.br.cvc.hotel.enums.BrokerEnum;

public interface BrokerService {
	
	List<Hotel> request(final Integer code,final BrokerEnum brokerEnum) throws Exception;

}
