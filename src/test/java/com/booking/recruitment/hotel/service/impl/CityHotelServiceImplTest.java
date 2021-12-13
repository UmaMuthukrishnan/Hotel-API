package com.booking.recruitment.hotel.service.impl;

import java.util.List;

import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.service.CityHotelService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class CityHotelServiceImplTest {

    @Autowired
    CityHotelService cityHotelService;

    @Test
    @DisplayName("When hotels in a city are requested along with distance sort then sorted results are returned")
    void getHotelsByCitySortByAsDistance() {
        List<Hotel> hotelList = cityHotelService.getHotelsByCityAndDistance(1L,"distance");
        assertThat(hotelList).hasSize(4);
        assertThat(hotelList.get(0).getName()).contains("Monaghan Hotel");
        assertThat(hotelList.get(1).getName()).contains("Raymond of Amsterdam Hotel");
        assertFalse(hotelList.stream().anyMatch(Hotel::isDeleted));
    }

    @Test
    @DisplayName("When hotels in a city are requested then results are returned with sorting value as empty")
    void getHotelsByCityWithSortByDistanceAsEmpty() {
        List<Hotel> hotelList = cityHotelService.getHotelsByCityAndDistance(1L,"");
        assertThat(hotelList).hasSize(4);
        assertThat(hotelList.get(0).getName()).contains("Monaghan Hotel");
        assertThat(hotelList.get(1).getName()).contains("McZoe Trescothiks Hotel");
        assertFalse(hotelList.stream().anyMatch(Hotel::isDeleted));
    }

    @Test
    @DisplayName("When hotels in a city are requested then results are returned with sorting value as null")
    void getHotelsByCityWithoutSortByDistance() {
        List<Hotel> hotelList = cityHotelService.getHotelsByCityAndDistance(1L,null);
        assertThat(hotelList).hasSize(4);
        assertThat(hotelList.get(0).getName()).contains("Monaghan Hotel");
        assertThat(hotelList.get(1).getName()).contains("McZoe Trescothiks Hotel");
        assertFalse(hotelList.stream().anyMatch(Hotel::isDeleted));
    }

}
