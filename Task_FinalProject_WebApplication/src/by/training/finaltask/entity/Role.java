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

    public int getValue() {
        return this.ordinal();
    }
    public String getName(){return this.value;}

    //todo: fix static functions
    public static Role valueOf(int idx)
    {
        return Role.values()[idx];
    }
}
