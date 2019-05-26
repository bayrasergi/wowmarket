package mpi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "`request`")
public class Request {
    @Id
    @GeneratedValue
    @Column(name = "request_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requested_item_id")
    private Item requestedItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_user_id")
    private User buyerUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_user_id")
    private User sellerUser;

    @Column
    private int count;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_request_id")
    private Request parentRequest;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentRequest")
    private List<Request> childrenRequests;

    @Override
    public String toString() {
        return String.format("id=%d;requested=%s;children=[%s]",
                id,
                "asd",
                childrenRequests.stream().map(c -> Integer.toString(c.id)).collect(Collectors.joining(",")));
    }
}
