package by.training.finaltask.entity;

public enum PetStatus {
    ADOPTED("Adopted"),
    SHELTERED("Sheltered"),
    DEAD("Dead");

    private String value;

    PetStatus(String value)
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
