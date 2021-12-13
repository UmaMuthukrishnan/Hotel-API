package com.booking.recruitment.hotel.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.booking.recruitment.hotel.exception.ElementNotFoundException;
import com.booking.recruitment.hotel.model.City;
import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.repository.CityRepository;
import com.booking.recruitment.hotel.repository.HotelRepository;
import com.booking.recruitment.hotel.service.CityHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

@Service
public class CityHotelServiceImpl implements CityHotelService {

    private final HotelRepository hotelRepository;
    private final CityRepository cityRepository;

    @Autowired
    CityHotelServiceImpl(HotelRepository hotelRepository, CityRepository cityRepository) {
        this.hotelRepository = hotelRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<Hotel> getHotelsByCityAndDistance(Long cityId, String distance) {
        final City city = cityRepository
                .findById(cityId)
                .orElseThrow(() -> new ElementNotFoundException("Could not find city with ID provided"));
        final List<Hotel> cityHotelList = hotelRepository.findAllHotels().stream()
                .filter(hotel -> cityId.equals(hotel.getCity().getId()))
                .collect(Collectors.toList());
        if ((nonNull(distance) && !distance.isEmpty()) && isNotEmpty(cityHotelList)) {
            cityHotelList.sort(Comparator.comparingDouble(h -> getDistance(h.getLatitude(), h.getLongitude(), city.getCityCentreLatitude(), city.getCityCentreLongitude())));
        }
        return cityHotelList;
    }

    private static double getDistance(double latitudeOne, double longitudeOne, double latitudeTwo, double longitudeTwo) {
        final double R = 6372.8;
        final double dLat = Math.toRadians(latitudeTwo - latitudeOne);
        final double dLon = Math.toRadians(longitudeTwo - longitudeOne);
        latitudeOne = Math.toRadians(latitudeOne);
        latitudeTwo = Math.toRadians(latitudeTwo);
        double a =
                Math.pow(Math.sin(dLat / 2), 2)
                        + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(latitudeOne) * Math.cos(latitudeTwo);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }
}
