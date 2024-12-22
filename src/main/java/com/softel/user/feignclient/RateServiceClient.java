package com.softel.user.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.softel.user.response.RateServiceResponse;

@FeignClient(name = "RATE-SERVICE", url = "http://localhost:8083")
public interface RateServiceClient {

    @GetMapping(value="/ratings/hotels/{hotelId}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RateServiceResponse>> getRatingByHotelId(@PathVariable("hotelId") String hotelId);

    @GetMapping(value="/ratings/hotels/ratingNo/{ratingNo}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RateServiceResponse>> getListOfHotelIdsBasedOnRating(@PathVariable("ratingNo") String ratingNo);
}
