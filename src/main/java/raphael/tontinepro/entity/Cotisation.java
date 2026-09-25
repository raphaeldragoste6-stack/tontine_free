package raphael.tontinepro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cotisations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cotisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adhesion_id", nullable = false)
    private Adhesion adhesion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    @Column(nullable = false)
    private Double montant;

    private LocalDateTime dateCotisation;

    private Boolean paye;
}