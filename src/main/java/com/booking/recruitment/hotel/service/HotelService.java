package com.booking.recruitment.hotel.service;

import com.booking.recruitment.hotel.model.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HotelService {
  List<Hotel> getAllHotels();

  List<Hotel> getHotelsByCity(Long cityId);

  Hotel createNewHotel(Hotel hotel);

  Hotel getHotelById(Long hotelId);

  void deleteHotelById(Long hotelId);

//  List<Hotel> getHotelsByCityAndDistance(Long cityId);

}
