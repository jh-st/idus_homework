package com.test.develop.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.jdt.internal.compiler.util.Sorting;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class SearchVo {

    private String keyword;

    @ApiParam(value = "Current page", defaultValue = "0", required = true)
    protected Integer page = 0;

    @ApiParam(value = "Data count of page", defaultValue = "20", required = true)
    protected int size = 20;

    @ApiParam(value = "By Line up", defaultValue = "PK", required = true)
    protected Sorting orderBy;

    public PageRequest getPageRequest() {
        return PageRequest.of(this.getPage(), this.getSize(), Sort.by("id").descending());
    }

}
