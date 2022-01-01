package com.cloud_course.demo.amazon.s3.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UploadedFileResponse {

    private LocalDateTime creationTime;
    private String fileName;
}
