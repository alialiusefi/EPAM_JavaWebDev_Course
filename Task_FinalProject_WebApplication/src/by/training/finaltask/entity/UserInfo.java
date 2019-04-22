package by.training.finaltask.entity;

import java.util.GregorianCalendar;
import java.util.Objects;

public final class UserInfo {

    private String email;
    private String firstName;
    private String lastName;
    private GregorianCalendar dateOfBirth;
    private int address;

    public UserInfo(String email, String firstName, String lastName,
                    GregorianCalendar dateOfBirth, int address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
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

    public GregorianCalendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(GregorianCalendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
        return getAddress() == userInfo.getAddress() &&
                getEmail().equals(userInfo.getEmail()) &&
                getFirstName().equals(userInfo.getFirstName()) &&
                getLastName().equals(userInfo.getLastName()) &&
                getDateOfBirth().equals(userInfo.getDateOfBirth());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getFirstName(), getLastName(), getDateOfBirth(), getAddress());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserInfo{");
        sb.append("email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", address=").append(address);
        sb.append('}');
        return sb.toString();
    }
}
