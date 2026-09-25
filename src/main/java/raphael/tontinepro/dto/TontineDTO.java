package raphael.tontinepro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raphael.tontinepro.enums.Frequence;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonFormat




public class TontineDTO {

    private Long id;

    @NotBlank(message = "Le nom de la tontine est obligatoire")
    private String nom;

    private String description;

    @NotNull(message = "Le montant de la cotisation est obligatoire")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le montant doit être supérieur à 0")
    private BigDecimal montantCotisation;

    @NotNull(message = "La fréquence est obligatoire")
    private Frequence frequence;

    @NotNull(message = "La date de début est obligatoire")
    private LocalDateTime dateDebut;

    private LocalDateTime dateCreation;
}