package com.pst.automation.file.converter;

import com.pst.automation.file.mapper.MappedAttributes;
import com.pst.automation.model.SchemaHeaders;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SchemaAttributesToMapConverter implements Function<List<String>, MappedAttributes> {

    private final SchemaHeaders schemaHeaders;

    public SchemaAttributesToMapConverter(SchemaHeaders schemaHeaders) {
        this.schemaHeaders = schemaHeaders;
    }

    @Override
    public MappedAttributes apply(List<String> strings) {
        Map<String, String> map = IntStream.range(0, schemaHeaders.getSchemaHeaders().size())
                .boxed()
                .collect(Collectors.toMap(schemaHeaders.getSchemaHeaders()::get, strings::get
                , (s, s2) -> s,LinkedHashMap::new));
        return new MappedAttributes(map);
    }
}
