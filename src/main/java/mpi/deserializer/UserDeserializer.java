package mpi.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import mpi.model.User;

import java.io.IOException;

import static mpi.util.JsonUtil.*;

public class UserDeserializer extends JsonDeserializer<User> {
    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        JsonNode json = jsonParser.getCodec().readTree(jsonParser);
        return deserialize(json);
    }

    public static User deserialize(JsonNode json) {
        if (json == null) {
            return null;
        }
        User user = new User();
        user.setId(getInt(json, "id"));
        user.setName(getString(json, "name"));
        user.setPassword(getString(json, "password"));
        user.setRole(getString(json, "role"));
        user.setUsername(getString(json, "username"));
        user.setLocked(getBoolean(json, "locked"));
        return user;
    }
}
