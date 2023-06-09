package com.jdsbbmq.wjxbx.dao.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QueryEntity {
    private String id;
    private String searchKeyWord;
    private String sortType;
    private String sort;
    private int offset;
    private int pageSize;
    private int currentPage;
    private String type;
}
