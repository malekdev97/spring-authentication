package com.malek.app.rest.Model;

<<<<<<< HEAD
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

=======
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
>>>>>>> 46fa39f76f4dfeac754130f9b0b52c5b4b4999f4
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
<<<<<<< HEAD

    private String password;

    // create join table user_role
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> role = new ArrayList<>();
=======
    private String password;

    // Defined a many to many relationship
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns =  {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles = new ArrayList<>();
>>>>>>> 46fa39f76f4dfeac754130f9b0b52c5b4b4999f4
}
