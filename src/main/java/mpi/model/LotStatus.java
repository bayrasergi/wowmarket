package mpi.model;

public enum LotStatus {
    SELLING("SELLING"),
    SOLD("SOLD");

    private String name;

    public String getName() {
        return name;
    }

    LotStatus(String name) {
        this.name = name;
    }

    public static LotStatus getByName(String name) {
        switch (name.toUpperCase()) {
            case "SELLING":
                return SELLING;
            case "SOLD":
                return SOLD;
            default:
                return null;
        }
    }
}
