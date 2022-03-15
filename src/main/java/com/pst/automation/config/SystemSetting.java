package com.pst.automation.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@Getter
public class SystemSetting {

    private static String inputFilePath = System.getProperty("user.dir");
    private static String inputFileName = "DSV input 1.txt";
    private static String outputFilePath = System.getProperty("user.dir");
    private static String outputFileName;

    private static boolean shouldSkipEmptyAttributes = true;
    private static String delimiter;
    private static boolean removeSpecialCharacters = true;
    private static String dateHeaderName = "dateOfBirth";

    static  {

        if (!Objects.isNull(System.getProperty("skipEmptyAttributes"))) {
            shouldSkipEmptyAttributes = false;
        }
        if (!Objects.isNull(System.getProperty("delimiter"))) {
            delimiter = System.getProperty("delimiter");
        }

        if (!Objects.isNull(System.getProperty("removeSpecialCharacters"))) {
            removeSpecialCharacters = true;
        }

        if (!Objects.isNull(System.getProperty("dateHeaderName"))) {
            dateHeaderName = System.getProperty("dateHeaderName");
        }

        if (!Objects.isNull(System.getProperty("inputFilePath"))) {
            inputFilePath = System.getProperty("inputFilePath");
        }
        if (!Objects.isNull(System.getProperty("inputFileName"))) {
            inputFileName = System.getProperty("inputFileName");
        }
        if (!Objects.isNull(System.getProperty("outputFilePath"))) {
            outputFilePath = System.getProperty("outputFilePath");
        }
        if (!Objects.isNull(System.getProperty("outputFileName"))) {
            outputFileName = System.getProperty("outputFileName");
        } else {
            //generate random Name
            outputFileName = "DSV output_"+System.currentTimeMillis() + ".jsonl";
        }
    }

    public static boolean isShouldSkipEmptyAttributes() {
        return shouldSkipEmptyAttributes;
    }

    public static String getDelimiter() {
        return delimiter;
    }

    public static String getDateHeaderName() {
        return dateHeaderName;
    }

    public static String getInputFilePath() {
        return inputFilePath;
    }

    public static boolean shouldRemoveSpecialCharacters() {
        return removeSpecialCharacters;
    }

    public static String getOutputFilePath() {
        return outputFilePath;
    }

    public static String getInputFileName() {
        return inputFileName;
    }

    public static String getOutputFileName() {
        return outputFileName;
    }


}
