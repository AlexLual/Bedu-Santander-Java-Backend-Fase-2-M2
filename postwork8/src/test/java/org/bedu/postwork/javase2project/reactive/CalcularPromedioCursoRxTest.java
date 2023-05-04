package org.bedu.postwork.javase2project.reactive;

import org.bedu.postwork.javase2project.model.Curso;
import org.bedu.postwork.javase2project.model.Estudiante;
import org.bedu.postwork.javase2project.model.Materia;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class CalcularPromedioCursoRxTest {

    private static final Curso CURSO = crearCurso();

    private static Curso crearCurso() {
        Estudiante estudiante1 = new Estudiante();
        estudiante1.setNombreCompleto("Jess Teran");

        Estudiante estudiante2 = new Estudiante();
        estudiante2.setNombreCompleto("Alejandro Hernandez");

        Estudiante estudiante3 = new Estudiante();
        estudiante3.setNombreCompleto("Jose Alberto");

        Materia materia = new Materia();
        materia.setNombre("PHP");

        Curso curso = new Curso();
        curso.setCiclo("2023");
        curso.setMateria(materia);

        Map<Estudiante, Integer> calificaciones = new HashMap<>();
        calificaciones.put(estudiante1, 7);
        calificaciones.put(estudiante2, 6);
        calificaciones.put(estudiante3, 8);

        curso.setCalificaciones(calificaciones);

        return curso;
    }

    @Test
    @DisplayName("Calcular Promedio")
    void calculaPromedio() {
        CalcularPromedioCursoRx calcularPromedioCursoRx = new CalcularPromedioCursoRx();

        calcularPromedioCursoRx.calcularPromedio(CURSO)
                .subscribe(v -> assertThat(v).isEqualTo(7.00, within(0.1)));
    }
}
/*
1. Se creó un método estático crearCurso() para encapsular la creación del objeto CURSO. Esto mejora la legibilidad y permite una mejor organización del código.
2. Se removió las importaciones no utilizadas, como import static org.junit.jupiter.api.Assertions.*;, para mantener el código más limpio.
3. En el método calculaPromedio(), se cambió el nombre del objeto sut a calcularPromedioCursoRx para mejorar la legibilidad.
 */
