package mpi.model;

public enum ItemType {
    CRAFTABLE("CRAFTABLE"),
    COLLECTIBLE("COLLECTIBLE"),
    STANDARD("STANDARD");

    private String name;

    public String getName() {
        return name;
    }

    ItemType(String name) {
        this.name = name;
    }

    public static ItemType getByName(String name) {
        switch (name.toUpperCase()) {
            case "CRAFTABLE":
                return CRAFTABLE;
            case "COLLECTIBLE":
                return COLLECTIBLE;
            default:
                return STANDARD;
        }
    }
}
