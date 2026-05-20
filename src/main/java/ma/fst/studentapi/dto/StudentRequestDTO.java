package ma.fst.studentapi.dto;

import jakarta.validation.constraints.*;

/**
 * DTO de requête — données reçues du client lors d'un POST ou PUT.
 *
 * Utilisation de Java Record :
 *   - Syntaxe concise (pas de boilerplate)
 *   - Immuable par construction
 *   - Accesseurs générés automatiquement : firstName(), lastName(), etc.
 *
 * Les annotations de validation sont vérifiées grâce à @Valid
 * dans le contrôleur.
 */
public record StudentRequestDTO(

        @NotBlank(message = "Le prénom est obligatoire")
        String firstName,

        @NotBlank(message = "Le nom est obligatoire")
        String lastName,

        @NotBlank(message = "L'email est obligatoire")
        @Email(message = "Format d'email invalide")
        String email,

        @NotBlank(message = "La filière est obligatoire")
        String major,

        @NotNull(message = "L'âge est obligatoire")
        @Min(value = 17, message = "L'âge minimal est 17 ans")
        @Max(value = 100, message = "L'âge maximal est 100 ans")
        Integer age

) {}
