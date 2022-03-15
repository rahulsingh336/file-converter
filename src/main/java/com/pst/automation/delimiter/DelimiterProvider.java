package com.pst.automation.delimiter;

import com.pst.automation.config.SystemSetting;
import com.pst.automation.predicate.PredicateCommon;

import java.util.Objects;

public class DelimiterProvider {

    private static DelimiterProvider INSTANCE;
    private String inputHeader;
    private String delimiter;

    public static DelimiterProvider getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DelimiterProvider();
        }
        return INSTANCE;
    }

    private void identifyDelimiter() {
          if (IsDelimiterSpecified()){
              delimiter =  SystemSetting.getDelimiter();
          } else {
              delimiter =  autoDetectDelimiter();
          }
    }

    private boolean IsDelimiterSpecified() {
      return PredicateCommon.IsDelimiterSpecified();
    }

    private String autoDetectDelimiter() {
        CSVDelimiterDetector csvDelimiterDetector = new CSVDelimiterDetector();
        return String.valueOf(csvDelimiterDetector.detectDelimiter(this.inputHeader));
    }

    public String getDelimiter() {
        if (Objects.isNull(delimiter)) {
            identifyDelimiter();
        }
        return delimiter;
    }

    public void setInputHeader(String inputHeader) {
        this.inputHeader = inputHeader;
    }
}
