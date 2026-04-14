package com.userservice.external.services;

import com.userservice.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotels/{hotelId}")
    @Bean
    Hotel getHotel(@PathVariable("hotelId") String hotelId);
   
}

