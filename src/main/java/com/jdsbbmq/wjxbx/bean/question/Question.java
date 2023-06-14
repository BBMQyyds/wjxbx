package com.jdsbbmq.wjxbx.bean.question;

import com.jdsbbmq.wjxbx.bean.choice.Choice;
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
    @Schema(description = "问题内容")
    @Size(min = 0, max = 200)
    private String title;
    @Schema(description = "问题序号")
    private int Sequence;
    @Schema(description = "问题选项列表")
    private List<Choice> choices;
}
