package com.example.lab9.domain;

import com.example.lab9.config.PassHash;

import javax.persistence.*;

import static com.example.lab9.utils.Utils.HTMLSpecialChars;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(columnDefinition = "CHAR(60)")
    private String password;
    private boolean enabled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = HTMLSpecialChars(username);
    }

    public void setPassword(String password) {
        this.password = PassHash.getInstance().encode(password);
    }

    public boolean equalsPassword(String pass){
        return PassHash.getInstance().matches(pass,password);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
