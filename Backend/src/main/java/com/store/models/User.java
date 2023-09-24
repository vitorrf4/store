package com.store.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name") private String name;
    @Column(name = "user_password") private String password;
    @OneToOne
    private Store store;

    public User() {
        id = -1L;
        name = "";
        password = "";
    }

    public User(Long id, String name, String password, @Nullable Store store) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.store = store;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
