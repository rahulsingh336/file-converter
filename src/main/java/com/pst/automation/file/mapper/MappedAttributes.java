package com.pst.automation.file.mapper;

import java.util.Map;

public class MappedAttributes {

    private final Map<String, String> mappedAttributesForLine;

    public MappedAttributes(Map<String, String> mappedAttributesForLine) {
        this.mappedAttributesForLine = mappedAttributesForLine;
    }

    public Map<String, String> getMappedAttributesForLine() {
        return mappedAttributesForLine;
    }
}
