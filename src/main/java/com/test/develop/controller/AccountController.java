package com.test.develop.controller;

import com.test.develop.dto.SearchVo;
import com.test.develop.service.AccountService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService service;

    @ApiOperation("회원목록 (페이징, 회원의 마지마막 주문정보, 검색기능)")
    @GetMapping
    public ResponseEntity<?> accounts(@ModelAttribute final SearchVo search) {
        return ResponseEntity.ok(service.getAccounts(search));
    }

    @ApiOperation("회원상세")
    @GetMapping("/{id}")
    public ResponseEntity<?> account(@PathVariable final Long id) {
        return ResponseEntity.ok(service.getAccount(id));
    }

}
