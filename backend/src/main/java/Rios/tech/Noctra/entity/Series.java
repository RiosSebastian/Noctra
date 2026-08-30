package Rios.tech.Noctra.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Series extends Content{
    @OneToMany(mappedBy = "series")
    private List<Season> seasons;
}
