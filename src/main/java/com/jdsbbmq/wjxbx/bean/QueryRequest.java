package com.jdsbbmq.wjxbx.bean;


import com.jdsbbmq.wjxbx.dao.entity.QueryEntity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QueryRequest {
    private String id;
    private String searchKeyWord;
    private String sortType;
    private String sort;
    private int offset;
    private int pageSize;
    private int currentPage;
    private String type;

    public QueryEntity ToQueryEntity() {
        QueryEntity queryEntity = new QueryEntity();
        queryEntity.setId(this.id);
        queryEntity.setSearchKeyWord(this.searchKeyWord);
        queryEntity.setSortType(this.sortType);
        queryEntity.setSort(this.sort);
        queryEntity.setOffset(this.offset);
        queryEntity.setCurrentPage(this.currentPage);
        queryEntity.setPageSize(this.pageSize);
        queryEntity.setType(type);
        return queryEntity;
    }
}
