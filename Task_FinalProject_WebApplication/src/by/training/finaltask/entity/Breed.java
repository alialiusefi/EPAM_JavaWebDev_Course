package by.training.finaltask.entity;

import java.util.Objects;

public final class Breed {

    private Integer id;
    private String name;
    private String description;
    private String origin;

    public Breed(Integer id, String name, String description, String origin) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Breed breed = (Breed) o;
        return Objects.equals(getId(), breed.getId()) &&
                Objects.equals(getName(), breed.getName()) &&
                Objects.equals(getDescription(), breed.getDescription()) &&
                Objects.equals(getOrigin(), breed.getOrigin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getOrigin());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Breed{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", origin='").append(origin).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
