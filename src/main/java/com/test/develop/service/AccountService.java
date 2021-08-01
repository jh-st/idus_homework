package com.test.develop.service;

import com.test.develop.dto.AccountListResponse;
import com.test.develop.dto.AccountRequest;
import com.test.develop.dto.AccountResponse;
import com.test.develop.dto.SearchVo;
import com.test.develop.entity.Account;
import com.test.develop.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository repository;

    public AccountResponse getAccount(final Long id) {
        return AccountResponse.builder().account(repository.getById(id)).build();
    }

    public Page<AccountListResponse> getAccounts(final SearchVo search) {
        return repository.pages(search);
    }

    @Transactional
    public Long save(final AccountRequest request) {
        return repository.save(Account.of(request)).getId();
    }

}
