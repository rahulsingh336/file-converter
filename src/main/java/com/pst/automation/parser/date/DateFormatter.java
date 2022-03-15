package com.pst.automation.parser.date;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class DateFormatter implements Function<String, String> {

    List<String> fromDateFormatHolder = Arrays.asList("yyyy-MM-dd", "yyyy/MM/dd", "dd-MM-yyyy", "yyyy/dd/MM");
    SimpleDateFormat toFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String apply(String date) {
        String dateCustomFormat = null;
        boolean flagMatchFound = false;
        for (String fromDate: fromDateFormatHolder) {
            if(flagMatchFound) break;
            try {
                SimpleDateFormat fdate = new SimpleDateFormat(fromDate);
                fdate.setLenient(false);
                Date dateFromFile = fdate.parse(date); // Parse it to the exisitng date pattern and return Date type
                toFormat.setLenient(false);
                dateCustomFormat = toFormat.format(dateFromFile); // format it to the date pattern you prefer
                flagMatchFound = true;
            } catch (ParseException e) {
                //Continue, so that next format can be analysed.
                log.error("Unable to parse date with format {}, ", date);
            }

        }
        return dateCustomFormat;
    }
}
