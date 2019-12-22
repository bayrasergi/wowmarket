package mpi.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import mpi.model.Profession;
import mpi.util.JsonUtil;

import java.io.IOException;

public class ProfessionDeserializer extends JsonDeserializer<Profession> {
    @Override
    public Profession deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        JsonNode json = jsonParser.getCodec().readTree(jsonParser);
        return deserialize(json);
    }

    public static Profession deserialize(JsonNode json) {
        if (json == null) {
            return null;
        }
        Profession profession = new Profession();
        profession.setName(JsonUtil.getString(json, "name"));
        profession.setCraft(JsonUtil.getBoolean(json, "craft"));
        profession.setId(JsonUtil.getInt(json, "id"));
        return profession;
    }
}
