package com.jdsbbmq.wjxbx.bean.question;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "web问题类")
public class Question {
    @Schema(description = "问题类别")
    private String type;
    @Schema(description = "问题题干")
    @Size(min = 0, max = 200)
    private String stem;
    @Schema(description = "问题选项列表")
    private List<String> choices;
    @Schema(description = "问题样式")
    private String format;
    @Schema(description = "与样式相关内容")
    private String related;
}
