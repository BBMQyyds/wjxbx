package com.jdsbbmq.wjxbx.bean.collect;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "收藏信息")
public class Collect {
    private String parentId;
    private String childId;

    //Type有project,file,questionnaire(待定）
    private String childType;
}
