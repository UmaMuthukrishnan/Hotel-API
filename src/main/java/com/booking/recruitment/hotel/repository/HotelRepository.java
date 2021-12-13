package com.booking.recruitment.hotel.repository;

import com.booking.recruitment.hotel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Modifying
    @Query(value = "update Hotel h set h.deleted=true where h.id =:hotelId")
    void deleteHotel(final Long hotelId);
}
