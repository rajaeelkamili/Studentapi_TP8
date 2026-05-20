package ma.fst.studentapi.exception;

/**
 * Exception métier levée quand une ressource demandée est introuvable.
 *
 * Hérite de RuntimeException → pas besoin de try/catch dans les
 * couches supérieures ; le GlobalExceptionHandler s'en charge.
 *
 * Utilisée par le service pour signaler qu'un étudiant
 * n'existe pas en base (GET /id, PUT /id, DELETE /id).
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
