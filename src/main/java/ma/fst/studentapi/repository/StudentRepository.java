package ma.fst.studentapi.repository;

import ma.fst.studentapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository Spring Data JPA pour l'entité Student.
 *
 * En héritant de JpaRepository<Student, Long>, les méthodes
 * suivantes sont disponibles sans écrire de code :
 *   - save(entity)
 *   - findAll()
 *   - findById(id)
 *   - existsById(id)
 *   - delete(entity)
 *   - deleteById(id)
 *   - count()
 *   - … et bien d'autres
 *
 * Les méthodes « dérivées » ci-dessous sont générées automatiquement
 * par Spring à partir de leur nom.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Recherche un étudiant par son adresse email.
     * SQL généré : SELECT * FROM students WHERE email = ?
     */
    Optional<Student> findByEmail(String email);

    /**
     * Vérifie rapidement l'existence d'un email en base.
     * SQL généré : SELECT count(*) > 0 FROM students WHERE email = ?
     */
    boolean existsByEmail(String email);
}
