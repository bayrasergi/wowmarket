package mpi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import mpi.deserializer.LotDeserializer;
import mpi.serializer.LotSerializer;

import javax.persistence.*;

@Data
@Entity
@Table(name = "lot")
@JsonSerialize(using = LotSerializer.class)
@JsonDeserialize(using = LotDeserializer.class)
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_id", columnDefinition = "serial")
    private int id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "seller_user_id")
    private User sellerUser;

    @Column
    private int count;

    @Column
    private int price;

    @Column
    private String comment;
}
