package mpi.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import mpi.model.Profession;
import mpi.model.dto.ProfessionDTO;

import java.io.IOException;

public class ProfessionSerializer extends JsonSerializer<Profession> {
    @Override
    public void serialize(Profession profession, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeObject(serialize(profession));
    }

    public static ProfessionDTO serialize(Profession profession) {
        ProfessionDTO professionDTO = new ProfessionDTO();
        professionDTO.setId(profession.getId());
        professionDTO.setName(profession.getName());
        professionDTO.setCraft(profession.isCraft());
        return professionDTO;
    }
}
