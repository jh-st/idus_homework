package com.test.develop.repository;

import com.test.develop.dto.AccountListResponse;
import com.test.develop.dto.SearchVo;
import org.springframework.data.domain.Page;

public interface AccountRepositoryCustom {

    Page<AccountListResponse> pages(final SearchVo searchVo);

}
