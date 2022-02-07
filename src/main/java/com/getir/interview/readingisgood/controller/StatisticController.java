package com.getir.interview.readingisgood.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getir.interview.readingisgood.dto.StatisticResponse;
import com.getir.interview.readingisgood.service.StatisticService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/statistics")
@AllArgsConstructor
public class StatisticController {
	
	private StatisticService statisticService;
	
	@GetMapping("{customer-id}")
    @Operation(summary = "Get statisctics by customer id")
    public ResponseEntity<List<StatisticResponse>> get(@PathVariable(value = "customer-id") Long customerId) {
        return ResponseEntity.status(HttpStatus.OK).body(statisticService.getStatistics(customerId));
    }
}
