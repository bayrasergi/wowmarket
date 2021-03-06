package mpi.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import mpi.model.Message;
import mpi.model.dto.MessageDTO;

import java.io.IOException;

public class MessageSerializer extends JsonSerializer<Message> {
    @Override
    public void serialize(Message message, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeObject(serialize(message));
    }

    public static MessageDTO serialize(Message message) {
        if (message == null) {
            return null;
        }
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(message.getId());
        messageDTO.setText(message.getText());
        messageDTO.setUser(UserSerializer.serialize(message.getUser()));
        messageDTO.setTimestamp(message.getTimestamp().getTime());
        return messageDTO;
    }
}
