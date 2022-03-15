package com.pst.automation.handler;

import com.pst.automation.file.converter.SchemaAttributesToMapConverter;
import com.pst.automation.file.mapper.MapToJsonConverter;
import com.pst.automation.file.split.AttributeSplitter;
import com.pst.automation.file.writer.FileWriter;
import com.pst.automation.model.SchemaHeaders;
import org.json.simple.JSONObject;

import java.util.function.Consumer;
import java.util.function.Function;

public class HandleLine implements Consumer<String> {

    private SchemaHeaders schemaHeaders;

    public HandleLine(SchemaHeaders schemaHeaders) {
        this.schemaHeaders = schemaHeaders;
    }

    @Override
    public void accept(String line) {
        //create dependencies
        AttributeSplitter attributeSplitter = new AttributeSplitter();
        SchemaAttributesToMapConverter schemaAttributesToMapConverter = new SchemaAttributesToMapConverter(schemaHeaders);
        MapToJsonConverter mapToJsonConverter = new MapToJsonConverter();
        FileWriter fileWriter = new FileWriter();

        //prepare pipeline
        Function<String, JSONObject> jsonObject = attributeSplitter
                                                    .andThen(schemaAttributesToMapConverter)
                                                    .andThen(mapToJsonConverter);
        //execute
        fileWriter.accept(jsonObject.apply(line));
    }
}
