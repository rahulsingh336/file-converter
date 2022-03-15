package com.pst.automation.file.split;

import com.pst.automation.delimiter.DelimiterProvider;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class AttributeSplitter implements Function<String, List<String>> {

    @Override
    public List<String> apply(String input) {
        return splitWithParser(input);
    }

    private List<String> splitWithParser(String input) {
       log.info("Splitting the attributes");
        List<String> tokens = new ArrayList<>();
        int startPosition = 0;
        boolean isInQuotes = false;
        for (int currentPosition = 0; currentPosition < input.length(); currentPosition++) {
            if (input.charAt(currentPosition) == '\"') {
                isInQuotes = !isInQuotes;
            } else if (input.charAt(currentPosition) == DelimiterProvider.getInstance().getDelimiter().toCharArray()[0] && !isInQuotes) {
                tokens.add(input.substring(startPosition, currentPosition));
                startPosition = currentPosition + 1;
            }
        }

        String lastToken = input.substring(startPosition);
        if (lastToken.equals(DelimiterProvider.getInstance().getDelimiter())) {
            tokens.add("");
        } else {
            tokens.add(lastToken);
        }
        log.debug("Tokens are {}", tokens);
        return tokens;
    }
}
