package ma.fst.studentapi.service;

import ma.fst.studentapi.dto.StudentRequestDTO;
import ma.fst.studentapi.dto.StudentResponseDTO;
import ma.fst.studentapi.entity.Student;
import ma.fst.studentapi.exception.ResourceNotFoundException;
import ma.fst.studentapi.mapper.StudentMapper;
import ma.fst.studentapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service métier de l'application.
 *
 * @Service → Spring gère cette classe comme un bean de la couche métier.
 *
 * Responsabilités :
 *   - Orchestrer les appels au repository
 *   - Appliquer les règles métier (vérifications, transformations)
 *   - Lever des exceptions compréhensibles en cas d'erreur
 *
 * Le contrôleur ne contient aucune logique : il délègue tout au service.
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper     studentMapper;

    /**
     * Injection par constructeur (recommandée par Spring).
     * Les champs final garantissent l'immuabilité après construction.
     */
    public StudentService(StudentRepository studentRepository,
                          StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper     = studentMapper;
    }

    // ----------------------------------------------------------------
    // CREATE — POST /api/students
    // ----------------------------------------------------------------

    /**
     * Enregistre un nouvel étudiant en base.
     *
     * Étapes :
     *  1. Convertir le DTO en entité via le mapper
     *  2. Persister l'entité (Hibernate génère l'id)
     *  3. Retourner un DTO de réponse avec l'id assigné
     */
    public StudentResponseDTO addStudent(StudentRequestDTO dto) {
        Student student      = studentMapper.toEntity(dto);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toResponseDTO(savedStudent);
    }

    // ----------------------------------------------------------------
    // READ ALL — GET /api/students
    // ----------------------------------------------------------------

    /**
     * Retourne la liste de tous les étudiants.
     * stream() + map() transforme chaque entité en DTO de réponse.
     */
    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponseDTO)
                .toList();
    }

    // ----------------------------------------------------------------
    // READ ONE — GET /api/students/{id}
    // ----------------------------------------------------------------

    /**
     * Recherche un étudiant par son identifiant.
     * Lance ResourceNotFoundException (→ 404) si absent.
     */
    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Étudiant introuvable avec l'id : " + id));
        return studentMapper.toResponseDTO(student);
    }

    // ----------------------------------------------------------------
    // UPDATE — PUT /api/students/{id}
    // ----------------------------------------------------------------

    /**
     * Met à jour les informations d'un étudiant existant.
     *
     * Étapes :
     *  1. Vérifier l'existence de l'étudiant (sinon 404)
     *  2. Mettre à jour les champs via le mapper
     *  3. Sauvegarder l'entité modifiée
     *  4. Retourner le DTO mis à jour
     */
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Étudiant introuvable avec l'id : " + id));

        studentMapper.updateEntityFromDTO(dto, student);
        Student updatedStudent = studentRepository.save(student);
        return studentMapper.toResponseDTO(updatedStudent);
    }

    // ----------------------------------------------------------------
    // DELETE — DELETE /api/students/{id}
    // ----------------------------------------------------------------

    /**
     * Supprime un étudiant par son identifiant.
     * Vérifie d'abord l'existence pour renvoyer un 404 explicite
     * plutôt qu'un comportement silencieux.
     */
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Étudiant introuvable avec l'id : " + id));
        studentRepository.delete(student);
    }
}
