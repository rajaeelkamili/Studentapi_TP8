package ma.fst.studentapi.entity;

import jakarta.persistence.*;

/**
 * Entité JPA représentant un étudiant en base de données.
 *
 * @Entity  → classe gérée par JPA / Hibernate
 * @Table   → nom explicite de la table SQL
 */
@Entity
@Table(name = "students")
public class Student {

    /** Clé primaire auto-incrémentée par MySQL */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Prénom — 100 caractères max, non null */
    @Column(nullable = false, length = 100)
    private String firstName;

    /** Nom de famille — 100 caractères max, non null */
    @Column(nullable = false, length = 100)
    private String lastName;

    /** Email — unique, 150 caractères max, non null */
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    /** Filière (ex. Informatique, Mathématiques…) */
    @Column(nullable = false, length = 100)
    private String major;

    /** Âge de l'étudiant */
    @Column(nullable = false)
    private Integer age;

    // ----------------------------------------------------------------
    // Constructeurs
    // ----------------------------------------------------------------

    /** Constructeur sans argument requis par JPA */
    public Student() {
    }

    /** Constructeur complet (utile dans les tests et le mapper) */
    public Student(Long id, String firstName, String lastName,
                   String email, String major, Integer age) {
        this.id        = id;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.email     = email;
        this.major     = major;
        this.age       = age;
    }

    // ----------------------------------------------------------------
    // Getters & Setters
    // ----------------------------------------------------------------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    @Override
    public String toString() {
        return "Student{id=" + id
                + ", firstName='" + firstName + '\''
                + ", lastName='"  + lastName  + '\''
                + ", email='"     + email     + '\''
                + ", major='"     + major     + '\''
                + ", age="        + age + '}';
    }
}
