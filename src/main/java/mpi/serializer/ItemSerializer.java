package mpi.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import mpi.model.Item;
import mpi.model.dto.ItemDTO;

import java.io.IOException;

public class ItemSerializer extends JsonSerializer<Item> {
    @Override
    public void serialize(Item item, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeObject(serialize(item));
    }

    public static ItemDTO serialize(Item item) {
        if (item == null) {
            return null;
        }
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setType(item.getType());
        return itemDTO;
    }
}
