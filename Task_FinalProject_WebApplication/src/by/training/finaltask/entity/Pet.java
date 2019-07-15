package by.training.finaltask.entity;

import java.util.GregorianCalendar;
import java.util.Objects;

public final class Pet {

    private int id;
    private String name;
    private String photoURL;
    private GregorianCalendar dateOfBirth;
    private double weight;
    private GregorianCalendar dateSheltered;
    private int shelterID;
    private int breedID;
    private PetStatus status;

    public Pet(int id, String name, String photoURL, GregorianCalendar dateOfBirth,
               double weight, GregorianCalendar dateSheltered,
               int shelterID, int breedID, PetStatus status) {
        this.id = id;
        this.name = name;
        this.photoURL = photoURL;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.dateSheltered = dateSheltered;
        this.shelterID = shelterID;
        this.breedID = breedID;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public GregorianCalendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(GregorianCalendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public GregorianCalendar getDateSheltered() {
        return dateSheltered;
    }

    public void setDateSheltered(GregorianCalendar dateSheltered) {
        this.dateSheltered = dateSheltered;
    }

    public int getShelterID() {
        return shelterID;
    }

    public void setShelterID(int shelterID) {
        this.shelterID = shelterID;
    }

    public int getBreedID() {
        return breedID;
    }

    public void setBreedID(int breedID) {
        this.breedID = breedID;
    }

    public PetStatus getStatus() {
        return status;
    }

    public void setStatus(PetStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return getId() == pet.getId() &&
                Double.compare(pet.getWeight(), getWeight()) == 0 &&
                getShelterID() == pet.getShelterID() &&
                getBreedID() == pet.getBreedID() &&
                getName().equals(pet.getName()) &&
                getPhotoURL().equals(pet.getPhotoURL()) &&
                getDateOfBirth().equals(pet.getDateOfBirth()) &&
                getDateSheltered().equals(pet.getDateSheltered()) &&
                getStatus() == pet.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPhotoURL(), getDateOfBirth(), getWeight(), getDateSheltered(), getShelterID(), getBreedID(), getStatus());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Pet{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", photoURL='").append(photoURL).append('\'');
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", weight=").append(weight);
        sb.append(", dateSheltered=").append(dateSheltered);
        sb.append(", shelterID=").append(shelterID);
        sb.append(", breedID=").append(breedID);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
