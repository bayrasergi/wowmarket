package mpi.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import mpi.model.Certificate;
import mpi.model.dto.CertificateDTO;

import java.io.IOException;

public class CertificateSerializer extends JsonSerializer<Certificate> {
    @Override
    public void serialize(Certificate certificate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeObject(serialize(certificate));
    }

    public static CertificateDTO serialize(Certificate certificate) {
        if (certificate == null) {
            return null;
        }
        CertificateDTO certificateDTO = new CertificateDTO();
        certificateDTO.setId(certificate.getId());
        certificateDTO.setUsername(certificate.getUser().getUsername());
        certificateDTO.setProfession(certificate.getProfession().getName());
        certificateDTO.setLink(certificate.getLink());
        return certificateDTO;
    }
}
