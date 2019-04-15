package by.training.finaltask.entity;

import java.util.Objects;

//todo: should i use a builder here

public final class Pet {

    private int id;
    private String name;
    private String photoURL;
    private int age;
    private double weight;
    private int vaccinationID;
    private int shelterID;
    private String breedName;

    public Pet(int id, String name, String photoURL, int age,
               double weight, int vaccinationID, int shelterID, String breed) {
        this.id = id;
        this.name = name;
        this.photoURL = photoURL;
        this.age = age;
        this.weight = weight;
        this.vaccinationID = vaccinationID;
        this.shelterID = shelterID;
        this.breedName = breed;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getVaccinationID() {
        return vaccinationID;
    }

    public void setVaccinationID(int vaccinationID) {
        this.vaccinationID = vaccinationID;
    }

    public int getShelterID() {
        return shelterID;
    }

    public void setShelterID(int shelterID) {
        this.shelterID = shelterID;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return getId() == pet.getId() &&
                getAge() == pet.getAge() &&
                Double.compare(pet.getWeight(), getWeight()) == 0 &&
                getVaccinationID() == pet.getVaccinationID() &&
                getShelterID() == pet.getShelterID() &&
                getName().equals(pet.getName()) &&
                getPhotoURL().equals(pet.getPhotoURL()) &&
                getBreedName().equals(pet.getBreedName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPhotoURL(),
                getAge(), getWeight(), getVaccinationID(), getShelterID(), getBreedName());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Pet{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", photoURL='").append(photoURL).append('\'');
        sb.append(", age=").append(age);
        sb.append(", weight=").append(weight);
        sb.append(", vaccinationID=").append(vaccinationID);
        sb.append(", shelterID=").append(shelterID);
        sb.append(", breedName='").append(breedName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
