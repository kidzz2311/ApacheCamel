package com.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mybatis.domain.City;

@Mapper
public interface CityMapper {

  // @Select("select id, name, state, country from city where state = #{state}")
  City findByState(@Param("state") String state);

  City findById(@Param("id") Long id);

  City findByName(@Param("name") String name);

  List<City> findAll();

  List<City> selectComplexCity(@Param("name") String name, @Param("state") String state);

  void insertCity(City city);

  void updateCity(City city);
  // City selectCityById(@Param("id")Long id);
  // @Select("select * from city")
  // List<City> findAll();

  // //create
  // @Insert("insert into city (name, state, country) values (#{name}, #{state},
  // #{country})")
  // void insert(City city);
}
