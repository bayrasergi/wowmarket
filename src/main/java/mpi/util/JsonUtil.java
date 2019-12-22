package mpi.util;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonUtil {

    public static String getString(JsonNode json, String... nodes) {
        json = get(json, nodes);
        if (json.isNull()) {
            return null;
        }
        return json.asText();
    }

    public static int getInt(JsonNode json, String... nodes) {
        json = get(json, nodes);
        if (json.isNull()) {
            return 0;
        }
        return json.asInt();
    }

    public static long getLong(JsonNode json, String... nodes) {
        json = get(json, nodes);
        if (json.isNull()) {
            return 0;
        }
        return json.asLong();
    }

    public static Boolean getBoolean(JsonNode json, String... nodes) {
        json = get(json, nodes);
        if (json.isNull()) {
            return false;
        }
        return json.asBoolean();
    }

    public static JsonNode get(JsonNode json, String... nodes) {
        if (json == null) {
            return null;
        }
        for (String node : nodes) {
            json = json.get(node);
            if (json == null) {
                return null;
            }
        }
        return json;
    }
}
