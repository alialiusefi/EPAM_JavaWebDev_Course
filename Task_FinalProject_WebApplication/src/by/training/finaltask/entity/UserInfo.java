package by.training.finaltask.entity;

import java.util.GregorianCalendar;
import java.util.Objects;

public final class UserInfo {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private GregorianCalendar dateOfBirth;
    private String address;
    private long phone;

    public UserInfo(Integer id, String email, String firstName, String lastName,
                    GregorianCalendar dateOfBirth, String address, long phone) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return getId() == userInfo.getId() &&
                getPhone() == userInfo.getPhone() &&
                getEmail().equals(userInfo.getEmail()) &&
                getFirstName().equals(userInfo.getFirstName()) &&
                getLastName().equals(userInfo.getLastName()) &&
                getDateOfBirth().equals(userInfo.getDateOfBirth()) &&
                getAddress().equals(userInfo.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getFirstName(), getLastName(), getDateOfBirth(), getAddress(), getPhone());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserInfo{");
        sb.append("id=").append(id);
        sb.append(", email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", address='").append(address).append('\'');
        sb.append(", phone=").append(phone);
        sb.append('}');
        return sb.toString();
    }
}
