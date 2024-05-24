package com.mybatis.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybatis.Service.CityService;
import com.mybatis.domain.City;
import lombok.RequiredArgsConstructor;

@RequestMapping("/cities")
@RestController
@RequiredArgsConstructor
public class CityRestController {

  private final CityService cityService;

  @GetMapping("{state}")
  ResponseEntity<?> getCity(@PathVariable("state") String state) {
    try {
      return ResponseEntity.ok().body(cityService.getCityByState(state));
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  // @GetMapping("{id}")
  // City getCity(@PathVariable("id") Long id) {
  // return cityMapper.selectCityById(id);
  // }

  // @PostMapping("/create")
  // void createCity(@RequestBody City city) {
  // cityMapper.insert(city);
  // }

  @GetMapping("/all")
  ResponseEntity<?> getAllCity() {
    try{
      return ResponseEntity.ok().body(cityService.findAll());
    } catch (RuntimeException e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/complex/{name}/{state}")
  List<City> getComplexCity(@PathVariable("name") String name, @PathVariable("state") String state) {
    return cityService.selectComplexCity(name, state);
  }

  @PostMapping("/create")
  ResponseEntity<?> createCity(@RequestBody City city) {
    try{
      cityService.insertCity(city);
      return ResponseEntity.ok().body("City created");
    } catch (RuntimeException e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PatchMapping("/update")
  ResponseEntity<?> updateCity(@RequestBody City city) {
    try{
      cityService.updateCity(city);
      return ResponseEntity.ok().body("City updated");
    } catch (RuntimeException e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
