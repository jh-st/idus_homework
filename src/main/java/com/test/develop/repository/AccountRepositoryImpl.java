package com.test.develop.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.develop.dto.AccountListResponse;
import com.test.develop.dto.SearchVo;
import com.test.develop.entity.Account;
import com.test.develop.entity.QAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

@Repository
public class AccountRepositoryImpl extends QuerydslRepositorySupport implements AccountRepositoryCustom {

    private final EntityManager entityManager;

    private final JPAQueryFactory jpaQueryFactory;

    public AccountRepositoryImpl(EntityManager entityManager) {
        super(Account.class);
        this.entityManager = entityManager;
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    private Page<AccountListResponse> getPage(final JPAQuery<AccountListResponse> query, final SearchVo search) {
        final List<AccountListResponse> list = Objects.requireNonNull(getQuerydsl())
                .applyPagination(search.getPageRequest(), query)
                .fetch();

        return new PageImpl<>(list, search.getPageRequest(), query.fetchCount());
    }

    @Override
    public Page<AccountListResponse> pages(final SearchVo search) {
        final QAccount qAccount = QAccount.account;

        final JPAQuery<AccountListResponse> query = jpaQueryFactory
                .select(
                        Projections.constructor(AccountListResponse.class, qAccount)
                )
                .from(qAccount)
                .where(condition(search));

        return getPage(query, search);
    }

    public Predicate condition(SearchVo search) {
        final BooleanBuilder builder = new BooleanBuilder();
        final String keyword = search.getKeyword();

        if (!ObjectUtils.isEmpty(keyword)) {
            builder.and(
                    QAccount.account.name.contains(keyword)
                            .or(QAccount.account.email.contains(keyword))
            );
        }

        return builder;
    }
}
