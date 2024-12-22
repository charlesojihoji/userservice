package com.softel.user.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.softel.user.response.HotelServiceResponse;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE", url = "http://localhost:8082")
public interface HotelServiceClient {

	@GetMapping("/hotel")
    public ResponseEntity<List<HotelServiceResponse>> getAll();

    @GetMapping("/hotel/location/{place}")
    public ResponseEntity<List<HotelServiceResponse>> getListOfHotelsBasedOnLocation(@PathVariable("place") String place);

    @GetMapping("/hotel/name/{name}")
    public ResponseEntity<List<HotelServiceResponse>> getListOfHotelsByName(@PathVariable("name") String name);

    @GetMapping("/hotel/{id}")
    public ResponseEntity<HotelServiceResponse> getHotelById(@PathVariable("id") String id);


}