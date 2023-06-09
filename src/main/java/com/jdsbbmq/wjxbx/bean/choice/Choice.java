package com.jdsbbmq.wjxbx.bean.choice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "web选项类")
public class Choice {
    @Schema(description = "选项内容")
    @Size(min = 1, max = 60)
    private String Content;
    @Schema(description = "选项序号")
    private int sequence;
}
