package com.example.nba.utils;

import com.example.nba.model.Team;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import lombok.SneakyThrows;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import static com.google.common.io.Resources.getResource;

public class Utils {
    @SneakyThrows
    public static String resourceAsString(String resourcePath) {
        URL url = getResource(resourcePath);
        return Resources.toString(url, Charset.defaultCharset());
    }


    @SneakyThrows
    public static Team jsonToTeam(String json) {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Team.class);
    }

    @SneakyThrows
    public static List<Team> jsonToListOfOrders(String json) {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<List<Team>>() {
        });
    }
}
