package com.pst.automation.file.mapper;


import com.pst.automation.config.SystemSetting;
import com.pst.automation.parser.date.DateFormatter;
import com.pst.automation.predicate.PredicateCommon;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.pst.automation.constants.Constants.COMMA;

@Slf4j
public class MapToJsonConverter implements Function<MappedAttributes, JSONObject> {

    @Override
    public JSONObject apply(MappedAttributes mappedAttributes) {
        Map<String, Object> mapOfAttributes = filterIfRequired(mappedAttributes);

        //Format Date
        String dateHeaderName = SystemSetting.getDateHeaderName();
        if (mapOfAttributes.containsKey(dateHeaderName)) {
            mapOfAttributes.put(dateHeaderName, new DateFormatter().apply(String.valueOf(mapOfAttributes.get(dateHeaderName))));
        }
        log.info("Final map created is {}", mapOfAttributes);
        return new JSONObject(mapOfAttributes);
    }

    private Object sanitizeOutputValues(String value) {
        if (SystemSetting.shouldRemoveSpecialCharacters()) {
            if (value.startsWith("\"") && value.endsWith("\"")) {
               return value.replaceAll("[|+.^:,]",COMMA)
                       .trim().replaceAll("\"", " ").trim();
            } else if (StringUtils.isNumeric(value)){
                return Integer.valueOf(value);
            } else {
               return value.replaceAll("[|+.^:,]","").trim();
            }
        } else return value.trim();
    }

    private Map<String, Object> filterIfRequired(MappedAttributes mappedAttributes) {
        return mappedAttributes.getMappedAttributesForLine().entrySet().stream()
                 .filter(entry -> !PredicateCommon.IsAttributeValueEmpty().test(entry.getValue()))
                 .collect(Collectors.toMap(Map.Entry::getKey, value -> sanitizeOutputValues(value.getValue()), (s, s2) -> s, LinkedHashMap::new));
    }
}
