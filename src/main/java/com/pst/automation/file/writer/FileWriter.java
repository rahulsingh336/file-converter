package com.pst.automation.file.writer;


import com.pst.automation.file.FileOperation;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.function.Consumer;

@Slf4j
public class FileWriter implements Consumer<JSONObject> {

    @Override
    public void accept(JSONObject jsonObject) {
        try {
            log.info("Writing json object to output file");
            String outputFile = FileOperation.getInstance().getOutputFile();
            Files.write(Paths.get(outputFile), (jsonObject.toJSONString()
                            + System.lineSeparator()).getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
