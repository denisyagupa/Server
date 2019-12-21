package hello.entities;

import com.google.gson.annotations.Expose;
//import lombok.Getter;
//import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private String username;
    private String password;
    private String clock;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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


    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }
}
