package org.LLD.ticketingSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    Long id;
    String email;

    public User(String email) {
        this.email = email;
    }
}
