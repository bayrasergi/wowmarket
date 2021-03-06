package mpi.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import mpi.model.Request;

import java.io.IOException;

import static mpi.util.JsonUtil.*;

public class RequestDeserializer extends JsonDeserializer<Request> {
    @Override
    public Request deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        JsonNode json = jsonParser.getCodec().readTree(jsonParser);
        return deserialize(json);
    }

    public static Request deserialize(JsonNode json) {
        if (json == null) {
            return null;
        }
        Request request = new Request();
        request.setId(getInt(json, "id"));
        request.setStatus(getString(json, "status"));
        request.setCount(getInt(json, "count"));
        request.setParentRequest(new Request());
        request.getParentRequest().setId(getInt(json, "parentRequestId"));
        request.setRequestedItem(ItemDeserializer.deserialize(get(json, "requestedItem")));
        request.setSellerUser(UserDeserializer.deserialize(get(json, "sellerUser")));
        request.setBuyerUser(UserDeserializer.deserialize(get(json, "buyerUser")));
        request.setPrice(getInt(json, "price"));
        request.setPrepayment(getInt(json, "prepayment"));
        request.setPayment(getInt(json, "payment"));
        return request;
    }
}
