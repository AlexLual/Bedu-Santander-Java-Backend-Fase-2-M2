package org.bedu.postwork.javase2project.tools;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bedu.postwork.javase2project.model.Curso;
import org.bedu.postwork.javase2project.model.Estudiante;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ReporteCalificaciones {

    @Data
    @RequiredArgsConstructor
    static class Reporte {
        private final String nombreEstudiante;
        private final Integer calificacion;
    }

    public List<Reporte> ordenarPorNombre(Curso curso) {
        return generarReporte(curso.getCalificaciones(),
                Comparator.comparing(Reporte::getNombreEstudiante));
    }

    public List<Reporte> ordenarPorCalificacion(Curso curso) {
        return generarReporte(curso.getCalificaciones(),
                Comparator.comparing(Reporte::getCalificacion).reversed());
    }

    private List<Reporte> generarReporte(Map<Estudiante, Integer> calificaciones, Comparator<Reporte> comparator) {
        return calificaciones.entrySet().stream()
                .map(entry -> new Reporte(entry.getKey().getNombreCompleto(), entry.getValue()))
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}
/*
1. Se cambiaron los nombres de los métodos alfabetico y calificacion a ordenarPorNombre y ordenarPorCalificacion, respectivamente.
    Estos nombres son más descriptivos y siguen las convenciones de nomenclatura de Java.
2. Se utilizó el método Comparator.comparing() para crear los comparadores en lugar de las expresiones lambda.
    Esto mejora la legibilidad y sigue las buenas prácticas en Java.
3. Se cambió el nombre del método generaLista a generarReporte para seguir las convenciones de nomenclatura de Java y ser más descriptivo.
 */