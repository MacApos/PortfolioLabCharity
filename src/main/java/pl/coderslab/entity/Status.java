package pl.coderslab.entity;

public enum Status {
    DELIVERED("delivered"),
    UNDELIVERED("undelivered");
    private final String statusName;

    Status(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return statusName;
    }
}
