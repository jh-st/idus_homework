package com.test.develop.service;

import com.test.develop.dto.OrderRequest;
import com.test.develop.dto.OrderResponse;
import com.test.develop.entity.Orders;
import com.test.develop.repository.AccountRepository;
import com.test.develop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository repository;
    private final AccountRepository accountRepository;

    public List<OrderResponse> orders(Long accountId) {
        List<Orders> orders = repository.findByAccountId(accountId);
        return orders.stream().map(ord ->
                OrderResponse.builder()
                        .orderNumber(ord.getOrderNumber())
                        .goodsName(ord.getGoodsName())
                        .payTime(ord.getPayTime())
                        .build()
        ).collect(Collectors.toList());
    }

    @Transactional
    public Long save(final OrderRequest request) {
        request.setAccount(accountRepository.getById(request.getAccountId()));
        return repository.save(Orders.of(request)).getId();
    }

}
