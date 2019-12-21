package mpi.model;

import lombok.Getter;

public enum RequestStatus {
    CREATED("CREATED"),
    ASSIGNED("ASSIGNED"),
    READY("READY"),
    DELIVERED("DELIVERED"),
    REJECTED("REJECTED"),
    WITHDRAWN("WITHDRAWN"),
    CLOSED("CLOSED");

    @Getter
    private String status;

    RequestStatus(String status) {
        this.status = status;
    }
}
