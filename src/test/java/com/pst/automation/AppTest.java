package com.pst.automation;

import org.json.simple.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.RestoreSystemProperties;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for File DSV 1 Input and DSV 2 Input
 */
public class AppTest extends BaseTest {

    @Rule
    public final RestoreSystemProperties restoreSystemProperties = new RestoreSystemProperties();

    @Test
    public void testForFile_DSV_Input_1() throws IOException {
        //given
        File resourcesDirectoryInput = getInputFileDirectory();
        File resourcesDirectoryOutput = getOutputFileDirectory();
        setSystemProperty(resourcesDirectoryInput, resourcesDirectoryOutput, "DSV input 1.txt");
        String value = setAndGetOutputFileName();

        //when
        App.main(null);

        List<JSONObject> json = getOutPutJson(resourcesDirectoryOutput, value);

        //then
        //compare 2 json
        assertTrue(json.size() == expectedOutput.size()
                && json.containsAll(expectedOutput)
                && expectedOutput.containsAll(json));

    }

    @Test
    public void testForFile_DSV_Input_2() throws IOException {
        //given
        File resourcesDirectoryInput = getInputFileDirectory();
        File resourcesDirectoryOutput = getOutputFileDirectory();
        setSystemProperty(resourcesDirectoryInput, resourcesDirectoryOutput, "DSV input 2.txt");
        String value = setAndGetOutputFileName();

        //when
        App.main(null);

        List<JSONObject> json = getOutPutJson(resourcesDirectoryOutput, value);

        //then
        //compare 2 json
        assertTrue(json.size() == expectedOutput.size()
                && json.containsAll(expectedOutput)
                && expectedOutput.containsAll(json));

    }

}
