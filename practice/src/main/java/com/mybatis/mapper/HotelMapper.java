package com.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mybatis.domain.Hotel;

@Mapper
public interface HotelMapper {
    Hotel findById(@Param("id") Long id);

    Hotel findByName(@Param("name") String name);

    List<Hotel> findAll();

    void insertHotel(Hotel hotel);

    void updateHotel(Hotel hotel);
}