package com.xuecheng.base.model;

import lombok.Data;

import java.util.List;

@Data
public class PageR<T> {
    private List<T> items;
    private long counts;
    private long page;
    private long pageSize;
}
