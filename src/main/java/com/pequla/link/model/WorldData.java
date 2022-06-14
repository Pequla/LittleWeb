package com.pequla.link.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorldData {
    private String seed;
    private Long time;
    private String type;
}
