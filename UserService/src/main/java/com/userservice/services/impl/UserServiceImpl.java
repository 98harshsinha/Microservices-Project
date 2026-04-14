package com.userservice.services.impl;

import com.userservice.entities.Hotel;
import com.userservice.entities.Rating;
import com.userservice.entities.User;
import com.userservice.exceptions.ResourceNotFoundException;
import com.userservice.external.services.HotelService;
import com.userservice.repositories.UserRepository;
import com.userservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        //generate  unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    //get single user
    @Override
    public User getUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + userId, Rating[].class);
        logger.info("{} ", ratingsOfUser);
        List<Rating> ratings = Arrays.asList(ratingsOfUser);
        List<Rating> ratingList = ratings.stream().map(rating-> {
            Hotel hotel=restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(),Hotel.class);
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }
}
