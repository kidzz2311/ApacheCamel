package com.mybatis.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mybatis.Service.CityService;
import com.mybatis.domain.City;
import com.mybatis.mapper.CityMapper;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {
    @Mock
    private CityMapper cityMapper;

    @InjectMocks
    private CityService cityService;

    @Test
    public void testGetCityById() {
        City city = new City();
        city.setId(1L);
        city.setName("New York");
        city.setState("NY");
        city.setCountry("USA");
        when(cityMapper.findById(1L)).thenReturn(city);
        assertEquals(city, cityService.getCityById(1L));
    }

    @Test
    public void testGetAllCity() {
        List<City> cities = new ArrayList<>();
        cities.add(new City(1L, "New York", "NY", "USA"));
        when(cityMapper.findAll()).thenReturn(cities);
        Assertions.assertThat(cityService.findAll()).isNotNull();
    }

    @Test
    public void testSelectComplexCity() {
        List<City> cities = new ArrayList<>();
        cities.add(new City(1L, "New York", "NY", "USA"));
        cities.add(new City(2L, "New York", "NY", "USA"));
        when(cityMapper.selectComplexCity("New York", "NY")).thenReturn(cities);
        assertEquals(2,cityService.selectComplexCity("New York", "NY").size());
    }
}
