package mpi.model.dto;

import lombok.Data;

@Data
public class CertificateDTO {
    private int id;
    private String username;
    private String profession;
    private int professionId;
    private String link;
    private boolean approved;
}
