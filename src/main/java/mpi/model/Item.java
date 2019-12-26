package mpi.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mpi.serializer.ItemSerializer;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item")
@JsonSerialize(using = ItemSerializer.class)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", columnDefinition = "serial")
    private int id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private String description;
}
