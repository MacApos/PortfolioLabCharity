package pl.coderslab.entity;

public enum Status {
    TAKEN("taken"),
    NOT_TAKEN("notTaken");
    private final String statusName;

    Status(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return statusName;
    }
}
