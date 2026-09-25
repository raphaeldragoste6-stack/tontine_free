package raphael.tontinepro.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "adhesions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Adhesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membre_id", nullable = false)
    private Membre membre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tontine_id", nullable = false)
    private Tontine tontine;

    @NotNull(message = "L'ordre du tour est obligatoire")
    private Integer ordreTour;

    private LocalDate dateAdhesion;
}