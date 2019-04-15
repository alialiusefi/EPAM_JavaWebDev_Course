package by.training.finaltask.entity;

public enum Role {

    ADMINISTRATOR("Administrator"),
    STAFF("Staff"),
    GUEST("Guest");

    private String value;

    private Role(String value)
    {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getIdentity(){
        return this.ordinal();
    }
}
