package com.cloud_course.demo.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDetails {

    private LocalDateTime localDateTime;
    private List<Error> errors;
}
