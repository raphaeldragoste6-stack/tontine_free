package raphael.tontinepro.entity;

import jakarta.persistence.*;
import lombok.*;
import raphael.tontinepro.enums.StatutTour;

import java.time.LocalDate;

@Entity
@Table(name = "tours")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tontine_id", nullable = false)
    private Tontine tontine;

    private Integer numeroCycle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membre_beneficiaire_id", nullable = false)
    private Membre membreBeneficiaire;

    private LocalDate datePrevue;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private StatutTour statut = StatutTour.A_VENIR;
}