package mpi.util;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonUtil {

    public static String getString(JsonNode json, String... nodes) {
        json = get(json, nodes);
        return json.asText();
    }

    public static int getInt(JsonNode json, String... nodes) {
        json = get(json, nodes);
        return json.asInt();
    }

    public static long getLong(JsonNode json, String... nodes) {
        json = get(json, nodes);
        return json.asLong();
    }

    public static boolean getBoolean(JsonNode json, String... nodes) {
        json = get(json, nodes);
        return json.asBoolean();
    }

    public  static JsonNode get(JsonNode json, String... nodes) {
        for (String node : nodes) {
            json = json.get(node);
        }
        return json;
    }
}
