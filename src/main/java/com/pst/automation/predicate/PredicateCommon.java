package com.pst.automation.predicate;

import com.pst.automation.config.SystemSetting;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Predicate;

public class PredicateCommon {

    private static final Predicate<String> IS_ATTRIBUTE_VALUE_EMPTY = new IsAttributeValueEmpty();

    public static Predicate<String> IsAttributeValueEmpty() {
        return IS_ATTRIBUTE_VALUE_EMPTY;
    }

    public static boolean IsDelimiterSpecified() {
        return IsDelimiterSpecified.test();
    }

    private static class IsAttributeValueEmpty implements Predicate<String> {

        @Override
        public boolean test(String input) {
            return SystemSetting.isShouldSkipEmptyAttributes() && StringUtils.isEmpty(input);
        }

        @Override
        public String toString()
        {
            return "PredicateCommon.IsAttributeValueEmpty()";
        }
    }

    private static class IsDelimiterSpecified {

        public static boolean test() {
            return !StringUtils.isEmpty(SystemSetting.getDelimiter());
        }

        @Override
        public String toString()
        {
            return "PredicateCommon.IsDelimiterSpecified()";
        }
    }
}
