package com.booking.recruitment.hotel.service;

import java.util.List;

import com.booking.recruitment.hotel.model.Hotel;

public interface CityHotelService {

    List<Hotel> getHotelsByCityAndDistance(Long cityId,String distance);
}
