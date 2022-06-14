package com.pequla.link.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WebError {
    private String message;
    private Long timestamp;
}
