package com.mybatis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybatis.Service.HotelService;
import com.mybatis.domain.Hotel;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelRestController {
    private final HotelService hotelService;

    @GetMapping("/all")
    ResponseEntity<?> getAllHotel() {
        try {
            return ResponseEntity.ok().body(hotelService.findAll());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHotelById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(hotelService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getHotelByName(@PathVariable("name") String name) {
        try {
            return ResponseEntity.ok().body(hotelService.findByName(name));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createHotel(@RequestBody Hotel hotel) {
        try {
            hotelService.insertHotel(hotel);
            return ResponseEntity.ok().body("Hotel created");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateHotel(@RequestBody Hotel hotel) {
        try {
            hotelService.updateHotel(hotel);
            return ResponseEntity.ok().body("Hotel updated");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
