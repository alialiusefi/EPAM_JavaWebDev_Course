package by.training.finaltask.entity;

import java.util.Objects;

public final class Shelter {

    private int id;
    private String name;
    private String location;

    public Shelter(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shelter shelter = (Shelter) o;
        return getId() == shelter.getId() &&
                getName().equals(shelter.getName()) &&
                getLocation().equals(shelter.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLocation());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Shelter{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", location='").append(location).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
