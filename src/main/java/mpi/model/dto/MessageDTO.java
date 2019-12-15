package mpi.model.dto;

import lombok.Data;

@Data
public class MessageDTO {
    private int id;
    private String username;
    private long timestamp;
    private String text;
}
