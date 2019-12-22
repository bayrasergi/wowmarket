package mpi.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import mpi.model.Item;
import mpi.model.Lot;
import mpi.model.User;
import mpi.util.JsonUtil;

import java.io.IOException;

import static mpi.util.JsonUtil.*;

public class LotDeserializer extends JsonDeserializer<Lot> {
    @Override
    public Lot deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        JsonNode json = jsonParser.getCodec().readTree(jsonParser);
        return deserialize(json);
    }

    public static Lot deserialize(JsonNode json) {
        if (json == null) {
            return null;
        }
        Lot lot = new Lot();
        lot.setComment(getString(json, "comment"));
        lot.setId(getInt(json, "id"));
        lot.setCount(getInt(json, "count"));
        lot.setPrice(getInt(json, "price"));
        lot.setItem(ItemDeserializer.deserialize(get(json, "item")));
        lot.setSellerUser(UserDeserializer.deserialize(get(json, "sellerUser")));
        lot.setStatus(getString(json, "status"));
        return lot;
    }
}
