package mpi.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import mpi.model.Lot;
import mpi.model.dto.LotDTO;

import java.io.IOException;

public class LotSerializer extends JsonSerializer<Lot> {
    @Override
    public void serialize(Lot lot, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeObject(serialize(lot));
    }

    public static LotDTO serialize(Lot lot) {
        if (lot == null) {
            return null;
        }
        LotDTO lotDTO = new LotDTO();
        lotDTO.setId(lot.getId());
        lotDTO.setComment(lot.getComment());
        lotDTO.setCount(lot.getCount());
        lotDTO.setItem(ItemSerializer.serialize(lot.getItem()));
        lotDTO.setPrice(lot.getPrice());
        lotDTO.setSellerUser(UserSerializer.serialize(lot.getSellerUser()));
        lotDTO.setStatus(lot.getStatus());
        return lotDTO;
    }
}
