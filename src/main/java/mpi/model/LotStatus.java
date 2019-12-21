package mpi.model;

public enum LotStatus {
    AVAILABLE("AVAILABLE"),
    SELL("SELL");

    private String name;

    public String getName() {
        return name;
    }

    LotStatus(String name) {
        this.name = name;
    }

    public static LotStatus getByName(String name) {
        switch (name.toUpperCase()) {
            case "AVAILABLE":
                return AVAILABLE;
            case "SELL":
                return SELL;
            default:
                return null;
        }
    }
}
