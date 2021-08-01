package com.test.develop.controller;

import com.test.develop.service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService service;

    @ApiOperation("단일 회원의 주문목록")
    @GetMapping("/{accountId}")
    public ResponseEntity<?> orders(@PathVariable Long accountId) {
        return ResponseEntity.ok(service.orders(accountId));
    }

}
