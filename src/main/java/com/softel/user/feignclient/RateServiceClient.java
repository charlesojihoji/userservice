package com.softel.user.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.softel.user.response.RateServiceResponse;

@FeignClient(name = "RATE-SERVICE")
public interface RateServiceClient {

	@GetMapping("/ratings")
    public ResponseEntity<List<RateServiceResponse>> getAll();
}
