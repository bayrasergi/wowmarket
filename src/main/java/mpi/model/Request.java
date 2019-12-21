package mpi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mpi.deserializer.RequestDeserializer;
import mpi.serializer.RequestSerializer;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "`request`")
@JsonSerialize(using = RequestSerializer.class)
@JsonDeserialize(using = RequestDeserializer.class)
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id", columnDefinition = "serial")
    private int id;

    @ManyToOne
    @JoinColumn(name = "requested_item_id")
    private Item requestedItem;

    @ManyToOne
    @JoinColumn(name = "buyer_user_id")
    private User buyerUser;

    @ManyToOne
    @JoinColumn(name = "seller_user_id")
    private User sellerUser;

    @Column
    private int price;

    @Column
    private int prepayment;

    @Column
    private int payment;

    @Column
    private int count;

    @Column
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_request_id")
    private Request parentRequest;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentRequest")
    @JsonIgnore
    private List<Request> childrenRequests;

}
