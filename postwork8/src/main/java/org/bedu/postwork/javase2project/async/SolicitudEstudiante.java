package org.bedu.postwork.javase2project.async;

import org.bedu.postwork.javase2project.model.Curso;
import org.bedu.postwork.javase2project.model.Estudiante;

public class SolicitudEstudiante {
    private Estudiante estudiante;
    private Curso curso;

    public SolicitudEstudiante(Estudiante estudiante, Curso curso) {
        if (estudiante == null || curso == null) {
            throw new IllegalArgumentException("Estudiante y curso no pueden ser nulos.");
        }
        this.estudiante = estudiante;
        this.curso = curso;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
/*
1. Se eliminaron los métodos setEstudiante y setCurso. Si el estudiante y el curso no están destinados a cambiar después de que se
    crea la instancia de SolicitudEstudiante, es preferible hacer que los campos sean final. Esto también garantiza la inmutabilidad
    de la clase SolicitudEstudiante, lo que puede ser útil en entornos concurrentes o para mantener la integridad de los datos.

2. Se agregaron una comprobación en el constructor para asegurar que los parámetros estudiante y curso no sean nulos. Esto ayuda
    a evitar errores causados por objetos nulos y garantiza que la instancia de SolicitudEstudiante siempre tenga datos válidos.
 */