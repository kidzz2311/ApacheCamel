package com.mybatis.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mybatis.domain.Hotel;
import com.mybatis.mapper.HotelMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelMapper hotelMapper;

    public Hotel findById(Long id) {
        Hotel hotel = hotelMapper.findById(id);
        if (hotel == null) {
            throw new RuntimeException("Hotel not found");
        }
        return hotel;
    }

    public Hotel findByName(String name) {
        Hotel hotel = hotelMapper.findByName(name);
        if (hotel == null) {
            throw new RuntimeException("Hotel not found");
        }
        return hotel;
    }

    public List<Hotel> findAll() {
        if (hotelMapper.findAll().isEmpty()) {
            throw new RuntimeException("No hotels found");
        }
        List<Hotel> hotels = hotelMapper.findAll();
        System.out.println(hotels);
        return hotels;
    }

    public void insertHotel(Hotel hotel) {
        if (hotelMapper.findByName(hotel.getName()) != null) {
            throw new RuntimeException("Hotel already exists");
        }
        hotelMapper.insertHotel(hotel);
    }

    public void updateHotel(Hotel hotel) {
        if (hotelMapper.findById(hotel.getId()) == null) {
            throw new RuntimeException("Hotel does not exist");
        }
        hotelMapper.updateHotel(hotel);
    }
}
