package mpi.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "profession")
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
