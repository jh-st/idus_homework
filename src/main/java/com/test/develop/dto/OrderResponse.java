package com.test.develop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.develop.entity.Orders;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {

    private String orderNumber;
    private String goodsName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime payTime;

    @Builder
    public OrderResponse(final String orderNumber, final String goodsName, final LocalDateTime payTime) {
        this.orderNumber = orderNumber;
        this.goodsName = goodsName;
        this.payTime = payTime;
    }

    public OrderResponse(final Orders order) {
        this.orderNumber = order.getOrderNumber();
        this.goodsName = order.getGoodsName();
        this.payTime = order.getPayTime();
    }

    public OrderResponse(final List<Orders> orders) {
        final Orders order = orders.get(orders.size()-1);
        this.orderNumber = order.getOrderNumber();
        this.goodsName = order.getGoodsName();
        this.payTime = order.getPayTime();
    }

}
