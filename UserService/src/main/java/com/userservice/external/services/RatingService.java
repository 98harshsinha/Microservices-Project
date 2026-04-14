//package com.lcwd.user.service.external.services;
//
//import com.lcwd.user.service.entities.Rating;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//
//@Service
//@FeignClient(name = "RATING-SERVICE")
//public interface RatingService {
//
//
//    //get
// //get all ratings
//    @GetMapping
//    @Bean
//    List<Rating> getRatings();
//
//    //get all by UserId
//    @GetMapping("/userId")
//    @Bean
//    List<Rating> getRatingByUserId(@PathVariable String userId);
//
//    //get all by hotel
//    @GetMapping("/hotelId")
//    @Bean
//    List<Rating> getRatingByHotelId(@PathVariable String hotelId);
//
//
//    //POST
//
//    @PostMapping("/ratings")
//    public ResponseEntity<Rating> createRating(Rating values);
//
//
//    //PUT
//    @PutMapping("/ratings/{ratingId}")
//    public ResponseEntity<Rating> updateRating(@PathVariable("ratingId") String ratingId, Rating rating);
//
//
//    @DeleteMapping("/ratings/{ratingId}")
//    public void deleteRating(@PathVariable String ratingId);
//}
