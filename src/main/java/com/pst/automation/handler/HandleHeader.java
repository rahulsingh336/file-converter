package com.pst.automation.handler;

import com.pst.automation.delimiter.DelimiterProvider;
import com.pst.automation.model.SchemaHeaders;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class HandleHeader implements BiFunction<String, SchemaHeaders, SchemaHeaders> {

    @Override
    public SchemaHeaders apply(String input, SchemaHeaders schemaHeaders) {
        DelimiterProvider delimiterProvider = DelimiterProvider.getInstance();
        delimiterProvider.setInputHeader(input);
        List<String> collect = Arrays.stream(input.split(Pattern.quote(delimiterProvider.getDelimiter()))).collect(Collectors.toList());
        schemaHeaders.getSchemaHeaders().addAll(collect);
        log.info("Schema Headers are {}", schemaHeaders);
        return schemaHeaders;
    }
}
