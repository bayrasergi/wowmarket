package mpi.model.dto;

import lombok.Data;

@Data
public class RequestDTO {
    private int id;
    private ItemDTO requestedItem;
    private String buyerUsername;
    private String sellerUsername;
    private LotDTO lot;
    private int count;
    private String status;
    private int parentRequestId;
}
