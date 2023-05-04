package org.bedu.postwork.javase2project.repository;

import org.bedu.postwork.javase2project.model.Curso;
import org.bedu.postwork.javase2project.model.Estudiante;
import org.bedu.postwork.javase2project.model.Materia;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ComponentScan(basePackages = "org.bedu.postwork.javase2project")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RepositoriesIntegrationTest {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @BeforeAll
    void cleanDatabases() {
        cursoRepository.deleteAll();
        materiaRepository.deleteAll();
        estudianteRepository.deleteAll();
    }

    @Test
    @DisplayName("Prueba de Cursos")
    void smokeTest() {
        Estudiante jose = createAndSaveEstudiante("Jose");
        Estudiante alex = createAndSaveEstudiante("Alex");
        Estudiante jess = createAndSaveEstudiante("Jess");

        Materia java = createAndSaveMateria("Java");
        Materia react = createAndSaveMateria("React.js");
        Materia php = createAndSaveMateria("PHP");

        Curso cursoJava = createAndSaveCurso(java, jose);
        Curso cursoReact = createAndSaveCurso(react, alex);
        Curso cursoPHP = createAndSaveCurso(php, jess);

        assertNotNull(jose.getId());
        assertNotNull(alex.getId());
        assertNotNull(jess.getId());

        assertNotNull(java.getId());
        assertNotNull(react.getId());
        assertNotNull(php.getId());

        assertNotNull(cursoJava.getId());
        assertNotNull(cursoReact.getId());
        assertNotNull(cursoPHP.getId());
    }

    private Estudiante createAndSaveEstudiante(String nombreCompleto) {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombreCompleto(nombreCompleto);
        return estudianteRepository.save(estudiante);
    }

    private Materia createAndSaveMateria(String nombre) {
        Materia materia = new Materia();
        materia.setNombre(nombre);
        return materiaRepository.save(materia);
    }

    private Curso createAndSaveCurso(Materia materia, Estudiante estudiante) {
        Curso curso = new Curso();
        curso.setCiclo("2023");
        curso.setMateria(materia);
        Map<Estudiante, Integer> calificaciones = new HashMap<>();
        calificaciones.put(estudiante, Curso.NO_CALIFICADO);
        curso.setCalificaciones(calificaciones);
        return cursoRepository.save(curso);
    }

}
/*
1. Se crearon los métodos para encapsular la creación y almacenamiento de Estudiante, Materia y Curso
    (createAndSaveEstudiante, createAndSaveMateria, createAndSaveCurso). Esto mejora la legibilidad y permite una mejor organización del código.
2. Se cambiaron los nombres de las variables para seguir las convenciones de Java, como cambiar Jose a jose.
3. Se creó un método assertIdsNotNull
 */