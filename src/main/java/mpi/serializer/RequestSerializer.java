package mpi.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import mpi.model.Request;
import mpi.model.dto.RequestDTO;

import java.io.IOException;

public class RequestSerializer extends JsonSerializer<Request> {
    @Override
    public void serialize(Request request, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeObject(serialize(request));
    }

    public static RequestDTO serialize(Request request) {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setId(request.getId());
        requestDTO.setBuyerUsername(request.getBuyerUser().getUsername());
        requestDTO.setSellerUsername(request.getSellerUser().getUsername());
        requestDTO.setCount(request.getCount());
        requestDTO.setLot(LotSerializer.serialize(request.getLot()));
        requestDTO.setParentRequestId(request.getParentRequest().getId());
        requestDTO.setRequestedItem(ItemSerializer.serialize(request.getRequestedItem()));
        requestDTO.setStatus(request.getStatus());
        return requestDTO;
    }
}
