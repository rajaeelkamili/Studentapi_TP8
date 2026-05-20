package ma.fst.studentapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Point d'entrée de l'application Student API.
 *
 * @SpringBootApplication combine :
 *   - @Configuration       : classe de configuration Spring
 *   - @EnableAutoConfiguration : active la configuration automatique
 *   - @ComponentScan       : scanne les composants du package courant
 */
@SpringBootApplication
public class StudentApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApiApplication.class, args);
    }
}
