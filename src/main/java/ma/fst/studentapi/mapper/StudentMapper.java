package ma.fst.studentapi.mapper;

import ma.fst.studentapi.dto.StudentRequestDTO;
import ma.fst.studentapi.dto.StudentResponseDTO;
import ma.fst.studentapi.entity.Student;
import org.springframework.stereotype.Component;

/**
 * Mapper manuel entre l'entité Student et les DTOs.
 *
 * @Component → Spring détecte cette classe et la gère comme un bean
 *              injectable via le constructeur.
 *
 * Pourquoi un mapper manuel plutôt que MapStruct ?
 *   - Aucune dépendance supplémentaire
 *   - Le code de transformation est entièrement visible et lisible
 *   - Idéal pour apprendre le passage entre couches
 */
@Component
public class StudentMapper {

    /**
     * Convertit un StudentRequestDTO en entité Student.
     * Utilisé lors d'un POST (création).
     * L'id n'est pas défini ici — il sera généré par la base.
     */
    public Student toEntity(StudentRequestDTO dto) {
        Student student = new Student();
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        student.setMajor(dto.major());
        student.setAge(dto.age());
        return student;
    }

    /**
     * Convertit une entité Student en StudentResponseDTO.
     * Utilisé pour construire toutes les réponses JSON de l'API.
     */
    public StudentResponseDTO toResponseDTO(Student student) {
        return new StudentResponseDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getMajor(),
                student.getAge()
        );
    }

    /**
     * Met à jour les champs d'une entité existante à partir d'un DTO.
     * Utilisé lors d'un PUT (modification) : on conserve le même objet
     * JPA (et donc le même id) pour ne pas casser les références.
     */
    public void updateEntityFromDTO(StudentRequestDTO dto, Student student) {
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        student.setMajor(dto.major());
        student.setAge(dto.age());
    }
}
