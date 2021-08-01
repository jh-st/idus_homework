package com.test.develop.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AccountRequest {

    private String name;
    private String nickname;
    private String password;
    private String tel;
    private String email;
    private String gender;

    @Builder
    public AccountRequest(String name, String nickname, String password, String tel, String email, String gender) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.tel = tel;
        this.email = email;
        this.gender = gender;
    }

}
