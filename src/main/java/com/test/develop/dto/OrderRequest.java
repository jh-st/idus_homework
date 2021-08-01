package com.test.develop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.develop.entity.Account;
import lombok.Builder;
import lombok.Data;

@Data
public class OrderRequest {

    private String orderNumber;
    private String goodsName;
    private Long accountId;

    @JsonIgnore
    private Account account;

    @Builder
    public OrderRequest(final String orderNumber, final String goodsName, final Long accountId) {
        this.orderNumber = orderNumber;
        this.goodsName = goodsName;
        this.accountId = accountId;
    }

}
