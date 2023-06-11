package com.jdsbbmq.wjxbx.service;

import com.jdsbbmq.wjxbx.dao.entity.QuestionnaireEntity;
import java.util.List;
public interface QuestionnaireService {
    /*
        查询
     */

    //查找一个项目下的所有问卷
    public List<QuestionnaireEntity> selectAllQuestionnaire(String projectId);

}
