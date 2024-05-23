package com.mybatis.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mybatis.domain.City;
import com.mybatis.mapper.CityMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityMapper cityMapper;

    public City getCityById(Long id){
        City city = cityMapper.findById(id);
        if(city == null){
            throw new RuntimeException("City not found");
        }
        return city;
    }

    public City getCityByState(String state){
        City city = cityMapper.findByState(state);
        if(city == null){
            throw new RuntimeException("City not found");
        }
        return city;
    }

    public List<City> findAll(){
        if (cityMapper.findAll().isEmpty()) {
            throw new RuntimeException("No cities found");
        }
        return cityMapper.findAll();
    }

    public List<City> selectComplexCity(String name, String state){
        return cityMapper.selectComplexCity(name, state);
    }

    public void insertCity(City city){
        if(city.getName().equals("") || city.getState().equals("") || city.getCountry().equals("")){
            throw new RuntimeException("Please fill in all fields");
        }
        if(cityMapper.findByName(city.getName()) != null){
            throw new RuntimeException("City already exists");
        }
        cityMapper.insertCity(city);
    }

    public void updateCity(City city){
        if(city.getName().equals("") || city.getState().equals("") || city.getCountry().equals("")){
            throw new RuntimeException("Please fill in all fields");
        }
        if(cityMapper.findById(city.getId()) == null){
            throw new RuntimeException("City does not exist");
        }
        cityMapper.updateCity(city);
    }
}
