package mpi.model.dto;

import lombok.Data;

@Data
public class MessageDTO {
    private int id;
    private UserDTO user;
    private long timestamp;
    private String text;
}
