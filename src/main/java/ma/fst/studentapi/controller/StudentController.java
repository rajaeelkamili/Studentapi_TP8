package ma.fst.studentapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import ma.fst.studentapi.dto.StudentRequestDTO;
import ma.fst.studentapi.dto.StudentResponseDTO;
import ma.fst.studentapi.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST — expose les opérations CRUD sur les étudiants.
 *
 * @RestController  → combine @Controller + @ResponseBody
 *                    (les retours sont sérialisés en JSON)
 * @RequestMapping  → préfixe commun à tous les endpoints : /api/students
 * @Tag             → section Swagger UI
 *
 * Routes exposées :
 *   POST   /api/students        → créer un étudiant
 *   GET    /api/students        → lister tous les étudiants
 *   GET    /api/students/{id}   → consulter un étudiant
 *   PUT    /api/students/{id}   → modifier un étudiant
 *   DELETE /api/students/{id}   → supprimer un étudiant
 */
@RestController
@RequestMapping("/api/students")
@Tag(name = "Étudiants", description = "Opérations CRUD sur les étudiants")
public class StudentController {

    private final StudentService studentService;

    /** Injection du service par constructeur */
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // ----------------------------------------------------------------
    // POST /api/students — Créer un étudiant
    // ----------------------------------------------------------------

    @Operation(summary = "Créer un étudiant",
               description = "Enregistre un nouvel étudiant et retourne l'objet créé avec son id.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Étudiant créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDTO addStudent(
            @Valid @RequestBody StudentRequestDTO dto) {
        return studentService.addStudent(dto);
    }

    // ----------------------------------------------------------------
    // GET /api/students — Lister tous les étudiants
    // ----------------------------------------------------------------

    @Operation(summary = "Lister tous les étudiants",
               description = "Retourne la liste complète des étudiants enregistrés.")
    @ApiResponse(responseCode = "200", description = "Liste retournée avec succès")
    @GetMapping
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    // ----------------------------------------------------------------
    // GET /api/students/{id} — Consulter un étudiant
    // ----------------------------------------------------------------

    @Operation(summary = "Consulter un étudiant par identifiant")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Étudiant trouvé"),
        @ApiResponse(responseCode = "404", description = "Étudiant introuvable")
    })
    @GetMapping("/{id}")
    public StudentResponseDTO getStudentById(
            @Parameter(description = "Identifiant de l'étudiant")
            @PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // ----------------------------------------------------------------
    // PUT /api/students/{id} — Modifier un étudiant
    // ----------------------------------------------------------------

    @Operation(summary = "Modifier un étudiant",
               description = "Met à jour les informations d'un étudiant existant.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Étudiant mis à jour"),
        @ApiResponse(responseCode = "400", description = "Données invalides"),
        @ApiResponse(responseCode = "404", description = "Étudiant introuvable")
    })
    @PutMapping("/{id}")
    public StudentResponseDTO updateStudent(
            @Parameter(description = "Identifiant de l'étudiant")
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDTO dto) {
        return studentService.updateStudent(id, dto);
    }

    // ----------------------------------------------------------------
    // DELETE /api/students/{id} — Supprimer un étudiant
    // ----------------------------------------------------------------

    @Operation(summary = "Supprimer un étudiant")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Étudiant supprimé"),
        @ApiResponse(responseCode = "404", description = "Étudiant introuvable")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(
            @Parameter(description = "Identifiant de l'étudiant")
            @PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
