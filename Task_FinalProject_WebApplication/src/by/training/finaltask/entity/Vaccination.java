package by.training.finaltask.entity;

import java.util.GregorianCalendar;
import java.util.Objects;

public final class Vaccination {

    private int id;
    private GregorianCalendar manufactureDate;
    private String diseaseName;

    public Vaccination(int id, GregorianCalendar manufactureDate, String diseaseName) {
        this.id = id;
        this.manufactureDate = manufactureDate;
        this.diseaseName = diseaseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GregorianCalendar getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(GregorianCalendar manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaccination that = (Vaccination) o;
        return getId() == that.getId() &&
                getManufactureDate().equals(that.getManufactureDate()) &&
                getDiseaseName().equals(that.getDiseaseName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getManufactureDate(), getDiseaseName());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Vaccination{");
        sb.append("id=").append(id);
        sb.append(", manufactureDate=").append(manufactureDate);
        sb.append(", diseaseName='").append(diseaseName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
