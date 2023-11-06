package com.hr_management_system_backend;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JSON_Maker {

    public static ObjectMapper objectMapper = new ObjectMapper();

    public static List<Map<String, String>> create_Json_Object(Object... args) {
        if (args.length % 2 != 0) {
            throw new IllegalArgumentException("Arguments must be provided in key-value pairs");
        }

        try {
            // Create a list of maps representing JSON objects
            List<Map<String, String>> jsonList = new ArrayList<>();
            Map<String, String> jsonMap = new HashMap<>();
            for (int i = 0; i < args.length; i += 2) {
                String key = args[i].toString();
                String value = args[i + 1].toString();
                jsonMap.put(key, value);
            }
            jsonList.add(jsonMap);

            return jsonList;
        } catch (Exception e) {
            throw new RuntimeException("Error while creating JSON object", e);
        }
    }
}
