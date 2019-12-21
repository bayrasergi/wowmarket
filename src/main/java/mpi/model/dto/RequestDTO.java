package mpi.model.dto;

import lombok.Data;

@Data
public class RequestDTO {
    private int id;
    private ItemDTO requestedItem;
    private UserDTO buyerUser;
    private UserDTO sellerUser;
    private LotDTO lot;
    private int count;
    private int price;
    private int payment;
    private int prepayment;
    private String status;
    private int parentRequestId;
}
