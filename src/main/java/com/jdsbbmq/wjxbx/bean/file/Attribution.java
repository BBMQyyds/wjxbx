package com.jdsbbmq.wjxbx.bean.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "文件归属信息")
public class Attribution {
    private String parentId;
    private String childId;

    //Type有project,file,questionnaire(待定）
    private String childType;
    private String content;
}
