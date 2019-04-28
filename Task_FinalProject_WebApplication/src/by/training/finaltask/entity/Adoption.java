package by.training.finaltask.entity;

import java.util.GregorianCalendar;
import java.util.Objects;

public final class Adoption {

    private int petID;
    private GregorianCalendar adoption_start;
    private GregorianCalendar adoption_end;
    private int userID;

    public Adoption(int petID, GregorianCalendar adoption_start,
                    GregorianCalendar adoption_end, int userID) {
        this.petID = petID;
        this.adoption_start = adoption_start;
        this.adoption_end = adoption_end;
        this.userID = userID;
    }

    public int getPetID() {
        return petID;
    }

    public void setPetID(int petID) {
        this.petID = petID;
    }

    public GregorianCalendar getAdoption_start() {
        return adoption_start;
    }

    public void setAdoption_start(GregorianCalendar adoption_start) {
        this.adoption_start = adoption_start;
    }

    public GregorianCalendar getAdoption_end() {
        return adoption_end;
    }

    public void setAdoption_end(GregorianCalendar adoption_end) {
        this.adoption_end = adoption_end;
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
        return getPetID() == adoption.getPetID() &&
                getUserID() == adoption.getUserID() &&
                getAdoption_start().equals(adoption.getAdoption_start()) &&
                Objects.equals(getAdoption_end(), adoption.getAdoption_end());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPetID(), getAdoption_start(), getAdoption_end(), getUserID());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AdoptionDAO{");
        sb.append("petID=").append(petID);
        sb.append(", adoption_start=").append(adoption_start);
        sb.append(", adoption_end=").append(adoption_end);
        sb.append(", userID=").append(userID);
        sb.append('}');
        return sb.toString();
    }
}
