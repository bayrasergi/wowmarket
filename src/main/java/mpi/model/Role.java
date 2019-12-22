package mpi.model;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER"),
    COLLECTOR("COLLECTOR"),
    CREATOR("CREATOR");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Role getByName(String name) {
        switch (name.toUpperCase()) {
            case "ADMIN":
                return ADMIN;
            case "COLLECTOR":
                return COLLECTOR;
            case "CREATOR":
                return CREATOR;
            case "USER":
                return USER;
            default:
                return null;
        }
    }
}
