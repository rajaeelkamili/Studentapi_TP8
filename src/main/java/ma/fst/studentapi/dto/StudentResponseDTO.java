package ma.fst.studentapi.dto;

/**
 * DTO de réponse — données renvoyées au client après chaque opération.
 *
 * Ce record inclut le champ id (généré par la base) que le client
 * a besoin de connaître pour les appels suivants (GET, PUT, DELETE).
 *
 * Séparer entité et DTO de réponse permet de :
 *   - ne pas exposer de champs internes (ex. version, timestamps…)
 *   - faire évoluer la base indépendamment du contrat API
 */
public record StudentResponseDTO(
        Long    id,
        String  firstName,
        String  lastName,
        String  email,
        String  major,
        Integer age
) {}
