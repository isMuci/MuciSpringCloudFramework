package com.muci.framework.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo {

    private Integer page;

    private Integer limit;

    public Boolean limited() {
        return limit != null;
    }

    public Integer getPage() {
        if (page == null || page < 0) {
            return 0;
        }
        return page;
    }

    public Integer getLimit() {
        if (limit == null || limit < 0) {
            return 0;
        }
        return limit;
    }
}