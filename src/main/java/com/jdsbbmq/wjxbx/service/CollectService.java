package com.jdsbbmq.wjxbx.service;

import com.jdsbbmq.wjxbx.bean.file.Attribution;
import java.util.List;
public interface CollectService {
    /*
        查询
     */

    //查询id下方的收藏信息
    public List<Attribution> selectAllCollect(String id);
}
