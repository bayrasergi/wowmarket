package mpi.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import mpi.model.Item;
import mpi.model.Request;
import mpi.model.User;

import java.io.IOException;

import static mpi.util.JsonUtil.getInt;
import static mpi.util.JsonUtil.getString;

public class RequestDeserializer extends JsonDeserializer<Request> {
    @Override
    public Request deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        JsonNode json = jsonParser.getCodec().readTree(jsonParser);
        return deserialize(json);
    }

    public static Request deserialize(JsonNode json) {
        Request request = new Request();
        request.setId(getInt(json, "id"));
        request.setStatus(getString(json, "status"));
        request.setCount(getInt(json, "count"));
        request.setParentRequest(new Request());
        request.getParentRequest().setId(getInt(json, "parentRequestId"));
        Item item = new Item();
        item.setId(getInt(json, "requestedItem", "id"));
        item.setName(getString(json, "requestedItem", "name"));
        request.setRequestedItem(item);
        request.setSellerUser(new User());
        request.getSellerUser().setUsername(getString(json, "sellerUsername"));
        request.setBuyerUser(new User());
        request.getBuyerUser().setUsername(getString(json, "buyerUsername"));
        request.setLot(LotDeserializer.deserialize(json.get("lot")));
        return request;
    }
}
