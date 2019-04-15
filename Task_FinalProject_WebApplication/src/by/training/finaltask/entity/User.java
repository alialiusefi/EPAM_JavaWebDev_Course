package by.training.finaltask.entity;

import java.util.Objects;

public final class User {

    private String email;
    private String username;
    private String password;
    private Role userRole;

    public User(String email, String username, Role userRole) {
        this.email = email;
        this.username = username;
        this.userRole = userRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getEmail().equals(user.getEmail()) &&
                getUsername().equals(user.getUsername()) &&
                getPassword().equals(user.getPassword()) &&
                getUserRole() == user.getUserRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getUsername(), getPassword(), getUserRole());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("email='").append(email).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", userRole=").append(userRole);
        sb.append('}');
        return sb.toString();
    }
}
