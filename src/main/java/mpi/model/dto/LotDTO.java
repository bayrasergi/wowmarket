package mpi.model.dto;

import lombok.Data;

@Data
public class LotDTO {
    private int id;
    private ItemDTO item;
    private UserDTO sellerUser;
    private int count;
    private int price;
    private String comment;
}
