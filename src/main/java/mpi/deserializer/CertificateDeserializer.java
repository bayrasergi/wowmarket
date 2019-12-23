package mpi.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import mpi.model.Certificate;
import mpi.model.Profession;
import mpi.model.User;
import mpi.util.JsonUtil;

import java.io.IOException;

import static mpi.util.JsonUtil.getInt;
import static mpi.util.JsonUtil.getString;

public class CertificateDeserializer extends JsonDeserializer<Certificate> {
    @Override
    public Certificate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        JsonNode json = jsonParser.getCodec().readTree(jsonParser);
        return deserialize(json);
    }

    public static Certificate deserialize(JsonNode json){
        if (json == null) {
            return null;
        }
        Certificate certificate = new Certificate();
        certificate.setProfession(new Profession());
        certificate.getProfession().setName(getString(json, "profession"));
        certificate.setUser(new User());
        certificate.getUser().setUsername(getString(json, "username"));
        certificate.setId(getInt(json, "id"));
        certificate.setLink(getString(json, "link"));
        return certificate;
    }
}
