package logica;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable {
    @Id
    private String user;
    private String name;
    private String password;

    public User() {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
