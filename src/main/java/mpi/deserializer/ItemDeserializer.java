package mpi.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import mpi.model.Item;
import mpi.util.JsonUtil;

import java.io.IOException;

public class ItemDeserializer extends JsonDeserializer<Item> {
    @Override
    public Item deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        JsonNode json = jsonParser.getCodec().readTree(jsonParser);
        return deserialize(json);
    }

    public static Item deserialize(JsonNode json) {
        if (json == null) {
            return null;
        }
        Item item = new Item();
        item.setId(JsonUtil.getInt(json, "id"));
        item.setName(JsonUtil.getString(json, "name"));
        item.setType(JsonUtil.getString(json, "type"));
        return item;
    }
}
