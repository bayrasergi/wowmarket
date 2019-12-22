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

    public static RequestStatus getStatusByName(String name) {
        switch (name.toUpperCase()) {
            case "CREATED":
                return CREATED;
            case "ASSIGNED":
                return ASSIGNED;
            case "READY":
                return READY;
            case "DELIVERED":
                return DELIVERED;
            case "REJECTED":
                return REJECTED;
            case "WITHDRAWN":
                return WITHDRAWN;
            case "CLOSED":
                return CLOSED;
        }
        return null;
    }
}
