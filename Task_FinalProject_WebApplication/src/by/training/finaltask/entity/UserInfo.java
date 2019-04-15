package by.training.finaltask.entity;

import java.util.Objects;

public final class UserInfo {

    private String email;
    private String firstName;
    private String lastName;
    private int age;
    private int address;

    public UserInfo(String email, String firstName,
                    String lastName, int age, int address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return getAge() == userInfo.getAge() &&
                getAddress() == userInfo.getAddress() &&
                getEmail().equals(userInfo.getEmail()) &&
                getFirstName().equals(userInfo.getFirstName()) &&
                getLastName().equals(userInfo.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getFirstName(), getLastName(), getAge(), getAddress());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserInfo{");
        sb.append("email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", age=").append(age);
        sb.append(", address=").append(address);
        sb.append('}');
        return sb.toString();
    }
}
