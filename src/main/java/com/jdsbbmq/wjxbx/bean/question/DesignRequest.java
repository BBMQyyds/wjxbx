package com.jdsbbmq.wjxbx.bean.question;

import lombok.*;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DesignRequest {
    private String id;
    private List<Question> questions;
}
