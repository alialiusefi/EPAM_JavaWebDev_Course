package by.training.finaltask.entity;

public enum Role {

    ADMINISTRATOR,
    STAFF,
    GUEST;

    private int value;

    public int getValue() {
        return value;
    }

    //todo: fix static functions
    public static Role valueOf(int idx)
    {
        switch(idx)
        {
            case 0: return ADMINISTRATOR;
            case 1: return STAFF;
            case 2: return GUEST;
            default: throw new IllegalArgumentException("Role was not found!");
        }
    }

    public int getIdentity(){
        return this.ordinal();
    }
}
