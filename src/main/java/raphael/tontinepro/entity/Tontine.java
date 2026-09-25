package raphael.tontinepro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import raphael.tontinepro.enums.Frequence;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tontines")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tontine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String description;

    private BigDecimal montantCotisation;

    @Enumerated(EnumType.STRING)
    private Frequence frequence;

    private LocalDateTime dateDebut;

    private LocalDateTime dateCreation;

    @PrePersist
    protected void onCreate() {
        this.dateCreation = LocalDateTime.now();
    }
}