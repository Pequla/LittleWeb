package com.pequla.link.model;

import lombok.*;

import java.util.Set;

@Data
@Builder
public class PlayerStatus {
    private int max;
    private int online;
    private Set<PlayerData> list;
}
