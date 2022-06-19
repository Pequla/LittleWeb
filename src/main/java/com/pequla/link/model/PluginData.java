package com.pequla.link.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PluginData {
    private String name;
    private String version;
    private List<String> authors;
    private String description;
    private String website;
}
