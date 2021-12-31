package com.cloud_course.demo.model.response;

import com.amazonaws.services.rekognition.model.Label;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ImageResponse {

    private String imageName;
    private List<Label> imageLabels;
}
