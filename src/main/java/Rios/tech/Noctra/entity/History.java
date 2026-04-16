package Rios.tech.Noctra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double progress;
    private LocalDateTime lastWatched;

    @ManyToOne
    private Profile profile;

    @ManyToOne
    private Content content;
}
