package by.training.finaltask.entity;

import java.util.GregorianCalendar;
import java.util.Objects;

public final class Adoption {

    private int id;
    private int petID;
    private GregorianCalendar adoptionStart;
    private GregorianCalendar adoptionEnd;
    private int userID;

    public Adoption(int id,int petID, GregorianCalendar adoptionStart,
                    GregorianCalendar adoptionEnd, int userID) {
        this.id = id;
        this.petID = petID;
        this.adoptionStart = adoptionStart;
        this.adoptionEnd = adoptionEnd;
        this.userID = userID;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getPetID() {
        return petID;
    }

    public void setPetID(int petID) {
        this.petID = petID;
    }

    public GregorianCalendar getAdoptionStart() {
        return adoptionStart;
    }

    public void setAdoptionStart(GregorianCalendar adoptionStart) {
        this.adoptionStart = adoptionStart;
    }

    public GregorianCalendar getAdoptionEnd() {
        return adoptionEnd;
    }

    public void setAdoptionEnd(GregorianCalendar adoptionEnd) {
        this.adoptionEnd = adoptionEnd;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adoption adoption = (Adoption) o;
        return getId() == adoption.getId() &&
                getPetID() == adoption.getPetID() &&
                getUserID() == adoption.getUserID() &&
                Objects.equals(getAdoptionStart(), adoption.getAdoptionStart()) &&
                Objects.equals(getAdoptionEnd(), adoption.getAdoptionEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPetID(), getAdoptionStart(), getAdoptionEnd(), getUserID());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Adoption{");
        sb.append("id=").append(id);
        sb.append(", petID=").append(petID);
        sb.append(", adoptionStart=").append(adoptionStart);
        sb.append(", adoptionEnd=").append(adoptionEnd);
        sb.append(", userID=").append(userID);
        sb.append('}');
        return sb.toString();
    }
}
