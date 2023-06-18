package com.jdsbbmq.wjxbx.bean.project;


import com.jdsbbmq.wjxbx.dao.entity.QueryEntity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QueryRequest {
    private String searchKeyWord;
    private String sortType;
    private String sort;
    private int offset;
    private int pageSize;
    private int star;
    public QueryEntity ToQueryEntity(){
        QueryEntity queryEntity = new QueryEntity();
        queryEntity.setSearchKeyWord(this.searchKeyWord);
        queryEntity.setSortType(this.sortType);
        queryEntity.setSort(this.sort);
        queryEntity.setOffset(this.offset);
        queryEntity.setPageSize(this.pageSize);
        queryEntity.setFlag(this.star);
        return queryEntity;
    }
}
