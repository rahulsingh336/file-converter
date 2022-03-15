package com.pst.automation.model;

import java.util.LinkedList;
import java.util.List;

public class SchemaHeaders {

    List<String> schemaHeaders;

    public SchemaHeaders() {
        this.schemaHeaders = new LinkedList<>();
    }

    public List<String> getSchemaHeaders() {
        return schemaHeaders;
    }
}
