package com.test.develop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.develop.dto.OrderRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 12)
    private String orderNumber;

    @Column(length = 100)
    private String goodsName;

    @CreationTimestamp
    private LocalDateTime payTime;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @Builder
    public Orders(final String orderNumber, final String goodsName, final Account account) {
        this.orderNumber = orderNumber;
        this.goodsName = goodsName;
        this.account = account;
    }

    public static Orders of(OrderRequest request) {
        return Orders.builder()
                .orderNumber(request.getOrderNumber())
                .goodsName(request.getGoodsName())
                .account(request.getAccount())
                .build();
    }

}
