package com.test.develop.dto;

import com.test.develop.entity.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AccountListResponse {

    private Long id;
    private String name;
    private String nickname;
    private String tel;
    private String email;
    private String gender;

    private OrderResponse lastOrder;

    @Builder
    public AccountListResponse(final Account account) {
        this.id = account.getId();
        this.name = account.getName();
        this.nickname = account.getNickname();
        this.tel = account.getTel();
        this.email = account.getEmail();
        this.gender = account.getGender();
        this.lastOrder = new OrderResponse(account.getOrders());
    }

}
