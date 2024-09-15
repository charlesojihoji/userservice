package com.softel.user.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.softel.user.response.HotelServiceResponse;

@FeignClient(name = "HOTEL-SERVICE", url = "http://localhost:8082")
public interface HotelServiceClient {

	@GetMapping("/hotel")
    public ResponseEntity<List<HotelServiceResponse>> getAll();
}