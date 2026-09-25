package raphael.tontinepro.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdhesionDTO {

    private Long id; // Ajout de l'ID obligatoire

    @NotNull(message = "Le membre est obligatoire")
    private Long membreId;

    @NotNull(message = "La tontine est obligatoire")
    private Long tontineId;

    @NotNull(message = "L'ordre du tour est obligatoire")
    private Integer ordreTour;

    private LocalDate dateAdhesion;
}