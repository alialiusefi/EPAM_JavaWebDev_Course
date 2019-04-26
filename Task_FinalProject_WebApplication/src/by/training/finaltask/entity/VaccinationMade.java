/*
package by.training.finaltask.entity;

import java.util.GregorianCalendar;
import java.util.Objects;

public final class VaccinationMade {

    private int petID;
    private int vaccinationID;
    private GregorianCalendar dateOfVaccination;

    public VaccinationMade(int petID, int vaccinationID,
                           GregorianCalendar dateOfVaccination) {
        this.petID = petID;
        this.vaccinationID = vaccinationID;
        this.dateOfVaccination = dateOfVaccination;
    }

    public int getPetID() {
        return petID;
    }

    public void setPetID(int petID) {
        this.petID = petID;
    }

    public int getVaccinationID() {
        return vaccinationID;
    }

    public void setVaccinationID(int vaccinationID) {
        this.vaccinationID = vaccinationID;
    }

    public GregorianCalendar getDateOfVaccination() {
        return dateOfVaccination;
    }

    public void setDateOfVaccination(GregorianCalendar dateOfVaccination) {
        this.dateOfVaccination = dateOfVaccination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VaccinationMade that = (VaccinationMade) o;
        return getPetID() == that.getPetID() &&
                getVaccinationID() == that.getVaccinationID() &&
                getDateOfVaccination().equals(that.getDateOfVaccination());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPetID(), getVaccinationID(), getDateOfVaccination());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("VaccinationMade{");
        sb.append("petID=").append(petID);
        sb.append(", vaccinationID=").append(vaccinationID);
        sb.append(", dateOfVaccination=").append(dateOfVaccination);
        sb.append('}');
        return sb.toString();
    }
}
*/
