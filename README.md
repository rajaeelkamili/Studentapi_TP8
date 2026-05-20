# Student API — Lab Spring Boot

API REST complète de gestion des étudiants, construite avec Spring Boot 3,
Spring Data JPA, MySQL et documentée avec Swagger UI.

---

## Prérequis

| Outil    | Version minimale |
|----------|-----------------|
| JDK      | 17              |
| Maven    | 3.8+            |
| MySQL    | 8.0+            |

---

## Démarrage rapide

### 1. Créer la base MySQL

```sql
CREATE DATABASE student_db;
```

### 2. Configurer la connexion

Ouvrir `src/main/resources/application.properties` et ajuster :

```properties
spring.datasource.username=root
spring.datasource.password=votre_mot_de_passe
```

### 3. Lancer l'application

```bash
mvn spring-boot:run
```

Hibernate crée automatiquement la table `students` au premier démarrage
(`ddl-auto=update`).

---

## Swagger UI

Ouvrir dans le navigateur :

```
http://localhost:8080/swagger-ui/index.html
```

---

## Endpoints

| Méthode | URL                    | Description                    | Code succès |
|---------|------------------------|--------------------------------|-------------|
| POST    | /api/students          | Créer un étudiant              | 201         |
| GET     | /api/students          | Lister tous les étudiants      | 200         |
| GET     | /api/students/{id}     | Consulter un étudiant par id   | 200         |
| PUT     | /api/students/{id}     | Modifier un étudiant           | 200         |
| DELETE  | /api/students/{id}     | Supprimer un étudiant          | 204         |

---

## Exemples JSON

### POST /api/students

```json
{
  "firstName": "Youssef",
  "lastName":  "Alaoui",
  "email":     "youssef.alaoui@example.com",
  "major":     "Informatique",
  "age":       20
}
```

**Réponse 201 :**
```json
{
  "id":        1,
  "firstName": "Youssef",
  "lastName":  "Alaoui",
  "email":     "youssef.alaoui@example.com",
  "major":     "Informatique",
  "age":       20
}
```

### PUT /api/students/1

```json
{
  "firstName": "Youssef",
  "lastName":  "Alaoui",
  "email":     "youssef.new@example.com",
  "major":     "Data Science",
  "age":       21
}
```

### Erreur 404 (id inexistant)

```json
{
  "type":   "about:blank",
  "title":  "Ressource introuvable",
  "status": 404,
  "detail": "Étudiant introuvable avec l'id : 99"
}
```

### Erreur 400 (validation)

```json
{
  "type":   "about:blank",
  "title":  "Erreur de validation",
  "status": 400,
  "detail": "Format d'email invalide"
}
```

---

## Architecture du projet

```
src/main/java/ma/fst/studentapi/
│
├── StudentApiApplication.java   ← Point d'entrée (@SpringBootApplication)
├── OpenApiConfig.java           ← Configuration Swagger
│
├── controller/
│   └── StudentController.java   ← Endpoints REST
│
├── dto/
│   ├── StudentRequestDTO.java   ← Données reçues (record + validation)
│   └── StudentResponseDTO.java  ← Données renvoyées (record)
│
├── entity/
│   └── Student.java             ← Entité JPA (table students)
│
├── exception/
│   ├── ResourceNotFoundException.java  ← Erreur 404 métier
│   └── GlobalExceptionHandler.java     ← Gestionnaire global
│
├── mapper/
│   └── StudentMapper.java       ← Conversion entité ↔ DTO
│
├── repository/
│   └── StudentRepository.java   ← Accès données (JpaRepository)
│
└── service/
    └── StudentService.java      ← Logique métier
```

---

## Scénario de test complet (Swagger)

1. **POST** → ajouter un étudiant → noter l'`id` retourné
2. **GET** `/api/students` → vérifier la présence de l'étudiant
3. **GET** `/api/students/{id}` → consulter l'étudiant
4. **PUT** `/api/students/{id}` → modifier la filière et l'email
5. **DELETE** `/api/students/{id}` → supprimer (réponse 204)
6. **GET** `/api/students/{id}` → vérifier le 404

---

## Technologies

- **Spring Boot 3.5**
- **Spring Web** — contrôleurs REST
- **Spring Data JPA** — persistance
- **Hibernate** — ORM
- **MySQL 8** — base de données
- **Bean Validation** — @NotBlank, @Email, @Min, @Max
- **Springdoc OpenAPI 2** — Swagger UI
- **Java 17 Records** — DTOs concis et immuables
