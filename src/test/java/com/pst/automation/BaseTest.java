package com.pst.automation;

import com.pst.automation.file.FileOperation;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Before;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    protected static List<JSONObject> expectedOutput;

    @Before
    public void init(){
        //build expected O/P
        expectedOutput = buildExpectedJsonList();

    }

    protected File getOutputFileDirectory() {
        return new File("src/test/resources/data/output");
    }

    protected File getInputFileDirectory() {
        return new File("src/test/resources/data/");
    }

    protected void setSystemProperty(File resourcesDirectoryInput, File resourcesDirectoryOutput, String inputFileName) {
        System.setProperty("inputFilePath", resourcesDirectoryInput.getAbsolutePath());
        System.setProperty("inputFileName", inputFileName);
        System.setProperty("outputFilePath", resourcesDirectoryOutput.getAbsolutePath());
    }

    protected String setAndGetOutputFileName() {
        String value = "DSV output_"+ System.currentTimeMillis()+".jsonl";
        FileOperation.getInstance().setOutputFile(String.join(FileSystems.getDefault().getSeparator(),
                getOutputFileDirectory().getAbsolutePath(),
                value));
        return value;
    }

    protected static List<JSONObject> buildExpectedJsonList() {
        File resourcesDirectoryOutput = new File("src/test/resources/data/expected.output");
        List<JSONObject> json = new ArrayList<>();
        try {
            // FileReader reads text files in the default encoding.
            String outPut = String.join(FileSystems.getDefault().getSeparator(), resourcesDirectoryOutput.getAbsolutePath(),
                    "expected.jsonl");
            FileReader fileReader = new FileReader(outPut);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line = bufferedReader.readLine()) != null) {
                JSONObject obj = (JSONObject) new JSONParser().parse(line);
                json.add(obj);
            }
            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + "fileName" + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + "fileName" + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return json;
    }

    protected List<JSONObject> getOutPutJson(File resourcesDirectoryOutput, String value) {
        String line;

        List<JSONObject> json = new ArrayList<>();
        try {
            // FileReader reads text files in the default encoding.
            String outPut = String.join(FileSystems.getDefault().getSeparator(), resourcesDirectoryOutput.getAbsolutePath(),
                    value);
            FileReader fileReader = new FileReader(outPut);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                JSONObject obj = (JSONObject) new JSONParser().parse(line);
                json.add(obj);
            }
            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + "fileName" + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + "fileName" + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return json;
    }
}
