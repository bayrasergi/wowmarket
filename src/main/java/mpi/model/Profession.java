package mpi.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import mpi.deserializer.ProfessionDeserializer;
import mpi.serializer.ProfessionSerializer;

import javax.persistence.*;

@Data
@Entity
@Table(name = "profession")
@JsonSerialize(using = ProfessionSerializer.class)
@JsonDeserialize(using = ProfessionDeserializer.class)
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profession_id", columnDefinition = "serial")
    @JsonValue
    private int id;

    @Column
    private String name;

    @Column
    private boolean craft;
}
