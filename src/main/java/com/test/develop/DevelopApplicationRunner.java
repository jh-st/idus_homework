package com.test.develop;

import com.test.develop.dto.AccountRequest;
import com.test.develop.dto.OrderRequest;
import com.test.develop.service.AccountService;
import com.test.develop.service.OrderService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@NoArgsConstructor
public class DevelopApplicationRunner implements ApplicationRunner {

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrderService orderService;

    @Override
    public void run(ApplicationArguments args) {

        Long[] ids = new Long[10];

        for (int i = 0; i < 10; i++) {
            ids[i] = accountService.save(
                    AccountRequest.builder()
                            .name("TESTER_" + i)
                            .nickname("NICKNAME_" + i)
                            .password("asdasdakjsdh")
                            .tel("010-1234-123" + i)
                            .email("test" + i + "@test.com")
                            .build());
        }

        for (Long id : ids) {
            for (int i = 0; i < 10; i++) {
                orderService.save(
                    OrderRequest.builder()
                            .orderNumber("ABCabcDEF"+id+i)
                            .goodsName("상품"+id+i)
                            .accountId(id)
                            .build());
            }
        }


    }

}
