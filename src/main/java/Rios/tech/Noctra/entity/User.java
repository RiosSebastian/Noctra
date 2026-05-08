package Rios.tech.Noctra.entity;

import Rios.tech.Noctra.util.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private Rol rol;

    @OneToMany(mappedBy = "user")
    private List<Profile> profiles;

    @OneToOne
    private Subscription subscription;
}
