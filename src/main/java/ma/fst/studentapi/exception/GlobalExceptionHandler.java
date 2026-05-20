package ma.fst.studentapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Gestionnaire global des exceptions de l'API.
 *
 * @RestControllerAdvice intercepte toutes les exceptions lancées
 * depuis les contrôleurs et renvoie une réponse JSON standardisée
 * au lieu d'une trace technique brute.
 *
 * Utilise ProblemDetail (RFC 7807) — supporté nativement par Spring 6+.
 * Format de réponse :
 * {
 *   "type": "about:blank",
 *   "title": "Ressource introuvable",
 *   "status": 404,
 *   "detail": "Étudiant introuvable avec l'id : 99",
 *   "instance": "/api/students/99"
 * }
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gère les cas où une ressource n'existe pas en base.
     * → HTTP 404 Not Found
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFound(ResourceNotFoundException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pd.setTitle("Ressource introuvable");
        pd.setDetail(ex.getMessage());
        return pd;
    }

    /**
     * Gère les erreurs de validation Bean Validation (@Valid).
     * → HTTP 400 Bad Request
     *
     * Retourne le message du premier champ invalide.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Erreur de validation");
        String detail = ex.getBindingResult().getFieldError() != null
                ? ex.getBindingResult().getFieldError().getDefaultMessage()
                : "Données invalides";
        pd.setDetail(detail);
        return pd;
    }

    /**
     * Capture toutes les autres exceptions non prévues.
     * → HTTP 500 Internal Server Error
     */
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setTitle("Erreur interne du serveur");
        pd.setDetail(ex.getMessage());
        return pd;
    }
}
