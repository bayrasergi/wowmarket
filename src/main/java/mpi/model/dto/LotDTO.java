package mpi.model.dto;

import lombok.Data;

@Data
public class LotDTO {
    private int id;
    private ItemDTO item;
    private String sellerUsername;
    private int count;
    private int price;
    private String comment;
}
