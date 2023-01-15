package com.pequla.link.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerData {
    private String id;
    private String name;
}
