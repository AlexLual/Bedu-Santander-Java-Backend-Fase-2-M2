package org.bedu.postwork.javase2project.persistence;

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

import static org.junit.jupiter.api.Assertions.*;

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
    void cleanDatabases(){
        cursoRepository.deleteAll();
        materiaRepository.deleteAll();
        estudianteRepository.deleteAll();
    }

    @Test
    @DisplayName("Prueba de Cursos")
    void smokeTest(){
        Estudiante Jose = new Estudiante();
        Estudiante Alex = new Estudiante();
        Estudiante Jess = new Estudiante();
        Jose.setNombreCompleto("Jose");
        Alex.setNombreCompleto("Alex");
        Jess.setNombreCompleto("Jess");
        estudianteRepository.save(Jose);
        estudianteRepository.save(Alex);
        estudianteRepository.save(Jess);

        Materia Java = new Materia();
        Materia React = new Materia();
        Materia PHP = new Materia();

        Java.setNombre("Java");
        React.setNombre("React.js");
        PHP.setNombre("PHP");

        materiaRepository.save(Java);
        materiaRepository.save(React);
        materiaRepository.save(PHP);

        Curso curso = new Curso();
        curso.setCiclo("2023");
        curso.setMateria(Java);
        Map<Estudiante, Integer> calificaciones = new HashMap<>();
        calificaciones.put(Jose, Curso.NO_CALIFICADO);
        curso.setCalificaciones(calificaciones);
        cursoRepository.save(curso);

        Curso curso1 = new Curso();
        curso1.setCiclo("2023");
        curso1.setMateria(React);
        Map<Estudiante, Integer> calificaciones1 = new HashMap<>();
        calificaciones1.put(Alex, Curso.NO_CALIFICADO);
        curso1.setCalificaciones(calificaciones1);
        cursoRepository.save(curso1);

        Curso curso2 = new Curso();
        curso2.setCiclo("2023");
        curso2.setMateria(PHP);
        Map<Estudiante, Integer> calificaciones2 = new HashMap<>();
        calificaciones2.put(Jess, Curso.NO_CALIFICADO);
        curso.setCalificaciones(calificaciones2);
        cursoRepository.save(curso2);

        assertNotNull(Jose.getId());
        assertNotNull(Jess.getId());
        assertNotNull(Alex.getId());

        assertNotNull(Java.getId());
        assertNotNull(React.getId());
        assertNotNull(PHP.getId());

        assertNotNull(curso.getId());
        assertNotNull(curso1.getId());
        assertNotNull(curso2.getId());
    }

}