package com.booking.recruitment.hotel.controller;

import java.util.List;

import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.service.CityHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityHotelController {

    private final CityHotelService cityHotelService;

    @Autowired
    public CityHotelController(CityHotelService cityHotelService) {
        this.cityHotelService = cityHotelService;
    }


    @GetMapping("/search/{cityId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Hotel> getHotelsByCityAndDistance(@PathVariable("cityId") final Long cityId, @RequestParam(value = "sortBy", required = false) String distance) {
        return cityHotelService.getHotelsByCityAndDistance(cityId, distance);
    }

}
