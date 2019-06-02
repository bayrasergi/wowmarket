package mpi.model;

import lombok.Getter;

public enum RequestStatus {
    CREATED("CREATED"),
    ASSIGNED("ASSIGNED"),
    READY("READY"),
    CLOSED("CLOSED");

    @Getter
    private String status;

    RequestStatus(String status) {
        this.status = status;
    }
}
