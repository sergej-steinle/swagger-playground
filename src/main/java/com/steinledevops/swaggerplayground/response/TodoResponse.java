package com.steinledevops.swaggerplayground.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoResponse {

    private long id;
    private String title;
    private String description;
    private int priority;
    private boolean completed;
}
