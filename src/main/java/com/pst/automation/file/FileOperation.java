package com.pst.automation.file;

import com.pst.automation.config.SystemSetting;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class FileOperation {

    private static FileOperation INSTANCE;

    private String inputFile = String.join(FileSystems.getDefault().getSeparator(),
            SystemSetting.getInputFilePath(),
            SystemSetting.getInputFileName());

    private String outputFile = String.join(FileSystems.getDefault().getSeparator(),
            SystemSetting.getOutputFilePath(),
            SystemSetting.getOutputFileName());

    public static FileOperation getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new FileOperation();
        }
        return INSTANCE;
    }

    public void checkIfAbleToReadAndWrite(){
        validateIfFileCanBeRead(this.inputFile);
        validateIfFileWritten(this.outputFile);
        log.info("Input/Output file Path can be read/written, Path for input is {}, output path is {}",
                this.inputFile, this.outputFile);
    }

    public String getInputFile() {
        return inputFile;
    }

    private void validateIfFileCanBeRead(String filePath) {
      if (!isFileReadAble(filePath)){
          log.error("Input File cannot be read, Please check input file path/name or move file to a location" +
                  "which is accessible by process  :: {}", filePath);
          throw new IllegalArgumentException("Unable to access/read file");
      }
    }

    private void validateIfFileWritten(String filePath) {
        try {
            createNewFile(filePath);
        } catch (IOException e) {
            log.error("Output File cannot be created, Please check out file path/name or move file to a location" +
                    "which is accessible by process, path :::: {}", filePath);
            throw new IllegalArgumentException("Unable to access/write file");
        }
        if (!isFileWritable(filePath)){
            log.error("Output File cannot be written, Please check out file path/name or move file to a location" +
                    "which is accessible by process");
            throw new IllegalArgumentException("Unable to access/read file");
        }

    }

    public String getOutputFile() {
        return outputFile;
    }

    public boolean isFileReadAble(String file) {
        return Files.isReadable(Paths.get(file));
    }

    public boolean isFileWritable(String filePath) {
        return Files.isWritable(Paths.get(filePath));
    }

    public Path createNewFile(String filePath) throws IOException {
        return Files.createFile(Paths.get(filePath));
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

}
