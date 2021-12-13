package com.booking.recruitment.hotel.repository;

import java.util.List;
import java.util.Optional;

import com.booking.recruitment.hotel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query(value = "select h from Hotel h where h.id =:hotelId and h.deleted <> true")
    Optional<Hotel> findHotelById(final Long hotelId);

    @Modifying
    @Query(value = "update Hotel h set h.deleted=true where h.id =:hotelId")
    void deleteHotel(final Long hotelId);

    @Query(value = "select h from Hotel h where h.deleted <> true")
    List<Hotel> findAllHotels();
}
