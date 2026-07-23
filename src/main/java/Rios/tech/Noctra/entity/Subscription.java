package Rios.tech.Noctra.entity;

import Rios.tech.Noctra.util.SubscriptionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SubscriptionType type;

    private BigDecimal price;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToOne(mappedBy = "subscription")
    private User user;
}