package Rios.tech.Noctra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numbre;

    @ManyToOne
    private Series series;

    @OneToMany(mappedBy = "season")
    private List<Episode> episodes;
}
