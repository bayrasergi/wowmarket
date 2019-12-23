package mpi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mpi.deserializer.CertificateDeserializer;
import mpi.serializer.CertificateSerializer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "certificate")
@JsonSerialize(using = CertificateSerializer.class)
@JsonDeserialize(using = CertificateDeserializer.class)
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificate_id", columnDefinition = "serial")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "profession_id")
    private Profession profession;

    @Column
    private boolean approved;

    @Column
    private String link;
}