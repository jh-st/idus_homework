package com.test.develop.entity;

import com.test.develop.dto.AccountRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(length = 20)
    private String nickname;

    private String password;

    @Column(length = 20)
    private String tel;

    @Column(length = 100)
    private String email;

    @Column(length = 1)
    private String gender;

    @CreationTimestamp
    private LocalDateTime registrationDt;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<Orders> orders;

    @Builder
    public Account(String name, String nickname, String password, String tel, String email, String gender) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.tel = tel;
        this.email = email;
        this.gender = gender;
    }

    public static Account of(AccountRequest request) {
        return Account.builder()
                .name(request.getName())
                .nickname(request.getNickname())
                .password(request.getPassword())
                .tel(request.getTel())
                .email(request.getEmail())
                .gender(request.getGender())
                .build();
    }

}
