package mpi.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import mpi.model.Message;
import mpi.model.User;
import mpi.util.JsonUtil;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

import static mpi.util.JsonUtil.*;

public class MessageDeserializer extends JsonDeserializer<Message> {
    @Override
    public Message deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        JsonNode json = jsonParser.getCodec().readTree(jsonParser);
        return deserialize(json);
    }

    public static Message deserialize(JsonNode json) {
        Message message = new Message();
        message.setId(getInt(json, "id"));
        message.setText(getString(json, "text"));
        message.setTimestamp(Timestamp.from(Instant.ofEpochMilli(getLong(json, "timestamp"))));
        message.setUser(new User());
        message.getUser().setUsername(getString(json, "username"));
        return message;
    }
}
