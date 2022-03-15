package com.pst.automation;

import com.pst.automation.file.FileOperation;
import com.pst.automation.handler.HandleHeader;
import com.pst.automation.handler.HandleLine;
import com.pst.automation.model.SchemaHeaders;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 *
 * txt fle to json file converter
 */
@Slf4j
public class App {

    public static void main( String[] args ) throws IOException {
        log.info("Starting File conversion");
        FileOperation.getInstance().checkIfAbleToReadAndWrite();
        String inputFile = FileOperation.getInstance().getInputFile();
        try(Stream<String> stream = Files.lines(Paths.get(inputFile))) {
            Iterator<String> i = stream.iterator();

            SchemaHeaders schemaHeaders = new SchemaHeaders();
            HandleHeader handleHeader = new HandleHeader();
            handleFirst(i.next(), schemaHeaders, handleHeader);

            HandleLine handleLine = new HandleLine(schemaHeaders);
            i.forEachRemaining(handleLine);
        }

    }

    private static void handleFirst(String next, SchemaHeaders schemaHeaders, HandleHeader handleHeader) {
        handleHeader.apply(next, schemaHeaders);
    }
}
