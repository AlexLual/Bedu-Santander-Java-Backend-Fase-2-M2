package org.bedu.postwork.javase2project.reactive;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.bedu.postwork.javase2project.model.Curso;
import org.bedu.postwork.javase2project.model.Estudiante;
import org.bedu.postwork.javase2project.model.Materia;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CalcularPromedioCursoRxTest {

    private static final Curso CURSO = new Curso();

    static {
        Estudiante estudiante1 = new Estudiante();
        estudiante1.setNombreCompleto("Jess Teran");

        Estudiante estudiante2 = new Estudiante();
        estudiante2.setNombreCompleto("Alejandro Hernandez");

        Estudiante estudiante3 = new Estudiante();
        estudiante3.setNombreCompleto("Jose Alberto");

        Materia materia = new Materia();
        materia.setNombre("PHP");

        CURSO.setCiclo("2023");
        CURSO.setMateria(materia);

        Map<Estudiante, Integer> calificaciones = new HashMap<>();
        calificaciones.put(estudiante1, 7);
        calificaciones.put(estudiante2, 6);
        calificaciones.put(estudiante3, 8);

        CURSO.setCalificaciones(calificaciones);
    }

    @Test
    @DisplayName("Calcular Promedio")
    void calculaPromedio(){
        CalcularPromedioCursoRx sut = new CalcularPromedioCursoRx();

        sut.calcularPromedio(CURSO)
                .subscribe(v -> assertThat(v).isEqualTo(7.00, within(0.1)));

    }
}