package org.bedu.postwork.javase2project.tools;

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


class ReporteCalificacionesTest {

    private static final Materia MATERIA = new Materia();
    private static final Estudiante ESTUDIANTE_1 = new Estudiante();
    private static final Estudiante ESTUDIANTE_2 = new Estudiante();
    private static final Estudiante ESTUDIANTE_3 = new Estudiante();
    private static final Curso CURSO = new Curso();

    private static ReporteCalificaciones.Reporte reporte1;
    private static ReporteCalificaciones.Reporte reporte2;
    private static ReporteCalificaciones.Reporte reporte3;

    static{
        MATERIA.setNombre("React");

        ESTUDIANTE_1.setNombreCompleto("Jose");
        ESTUDIANTE_2.setNombreCompleto("Jess");
        ESTUDIANTE_3.setNombreCompleto("Alex");

        CURSO.setCiclo("2023");
        CURSO.setMateria(MATERIA);

        Map<Estudiante,Integer> calificaciones = new HashMap<>();
        calificaciones.put(ESTUDIANTE_1, 8);
        calificaciones.put(ESTUDIANTE_2, 6);
        calificaciones.put(ESTUDIANTE_3, 10);

        CURSO.setCalificaciones(calificaciones);

        reporte1 = new ReporteCalificaciones.Reporte("Jose", 8);
        reporte2 = new ReporteCalificaciones.Reporte("Jess", 6);
        reporte3 = new ReporteCalificaciones.Reporte("Alex", 10);


    }

    @Test
    @DisplayName("Ordena alfabéticamente")
    void alfabetico(){
        ReporteCalificaciones reporteCalificaciones = new ReporteCalificaciones();

        assertThat(reporteCalificaciones.alfabetico(CURSO)).containsExactly(reporte3, reporte2, reporte1);
        System.out.println(CURSO.getCalificaciones());
    }


    @Test
    @DisplayName("Ordena por calificaciones")
    void calificaciones(){
        ReporteCalificaciones reporteCalificaciones = new ReporteCalificaciones();

        assertThat(reporteCalificaciones.calificacion(CURSO)).containsExactly(reporte3, reporte1, reporte2);
        System.out.println(CURSO.getCalificaciones());
    }

}