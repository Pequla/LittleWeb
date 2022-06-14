package com.pequla.link.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ServerStatus {
    private PlayerStatus players;
    private WorldData world;
    private List<String> plugins;
    private String version;
}
