package raphael.tontinepro.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CotisationDTO {

    private Long id;

    @NotNull(message = "L'ID de l'adhésion est obligatoire")
    private Long adhesionId;

    @NotNull(message = "L'ID du tour est obligatoire")
    private Long tourId;

    @NotNull(message = "Le montant est obligatoire")
    private Double montant;

    private LocalDateTime dateCotisation;

    private Boolean paye;
}