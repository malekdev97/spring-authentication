package com.malek.app.rest.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role {
<<<<<<< HEAD

=======
>>>>>>> 46fa39f76f4dfeac754130f9b0b52c5b4b4999f4
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
}
