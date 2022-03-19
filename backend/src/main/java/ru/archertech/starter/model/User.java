package ru.archertech.starter.model;

import java.security.Principal;

public class User {
    private final String email;

    public static User wrap(Principal principal) {
        return new User(principal.getName());
    }

    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                '}';
    }
}
