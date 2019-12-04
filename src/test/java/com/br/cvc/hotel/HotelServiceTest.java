package com.br.cvc.hotel;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.br.cvc.hotel.dto.Hotel;
import com.br.cvc.hotel.dto.Price;
import com.br.cvc.hotel.dto.Room;
import com.br.cvc.hotel.enums.BrokerEnum;
import com.br.cvc.hotel.service.BrokerService;
import com.br.cvc.hotel.service.HotelServiceImpl;

public class HotelServiceTest {
	
	@Mock
	private BrokerService brokerService;
	private HotelServiceImpl hotelServiceImpl;
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		hotelServiceImpl = new HotelServiceImpl();		
		hotelServiceImpl.setBrokerService(brokerService);
	}
	
	@Test
	public void priceTotal() throws Exception {
		final List<Hotel> list = new ArrayList<Hotel>();
		Hotel hotel = new Hotel();
		hotel.setCityCode(1020);
		hotel.setCityName("Santo Andre");
		hotel.setId(1);
		hotel.setName("Teste Hotel");
		
		Room room = new Room();
		room.setCategoryName("Suite");
		room.setRoomID(1);
		
		Price price = new Price();
		price.setAdult(100d);
		price.setChild(50d);
		
		room.setPrice(price);
		hotel.setRooms(new ArrayList<Room>());
		hotel.getRooms().add(room);
		list.add(hotel);
		
		when(brokerService.request(1020,BrokerEnum.AVAIL)).thenReturn(list);
		
		List<Hotel> retHotels = hotelServiceImpl.avail(1020, "2019-01-20", "2019-01-25",2,1);
		Assert.assertTrue(retHotels.get(0).getRooms().get(0).getTotalPrice().doubleValue() == 1071.42);
	}

}
