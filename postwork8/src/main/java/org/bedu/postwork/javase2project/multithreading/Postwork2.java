package org.bedu.postwork.javase2project.multithreading;

import org.bedu.postwork.javase2project.model.Curso;
import org.bedu.postwork.javase2project.model.Estudiante;
import org.bedu.postwork.javase2project.model.Materia;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Postwork2 {

    private static final int NUM_ESTUDIANTES = 20;

    public static void main(String[] args) {
        Random rnd = new Random();
        ExecutorService pool = Executors.newCachedThreadPool();

        Curso[] cursos = {
                crearCurso(rnd, "CiberSeguridad", 1),
                crearCurso(rnd, "Programación Estructural", 2),
                crearCurso(rnd, "Introduccion a Desarrollo Web", 3),
                crearCurso(rnd, "Angular Basico", 4)
        };

        for (Curso curso : cursos) {
            pool.execute(new CalculadorPromedioCurso(curso));
        }

        pool.shutdown();
    }

    private static Curso crearCurso(Random rnd, String nombreMateria, long idCurso) {
        Curso curso = new Curso();
        curso.setId(idCurso);

        Materia materia = new Materia();
        materia.setNombre(nombreMateria);
        curso.setMateria(materia);

        Map<Estudiante, Integer> calificaciones = generarCalificaciones(rnd);
        curso.setCalificaciones(calificaciones);

        return curso;
    }

    private static Map<Estudiante, Integer> generarCalificaciones(Random rnd) {
        Map<Estudiante, Integer> calificaciones = new HashMap<>();

        for (int i = 1; i <= NUM_ESTUDIANTES; i++) {
            Estudiante estudiante = new Estudiante();
            estudiante.setId((long) i);
            estudiante.setNombreCompleto("Estudiante " + i);

            calificaciones.put(estudiante, rnd.nextInt(10));
        }

        return calificaciones;
    }
}
/*
1. Se creó la constante NUM_ESTUDIANTES para evitar el uso de números mágicos en el código.
2. Se eliminó la impresión del nombre de cada estudiante, ya que no es necesario para el propósito principal del programa.
3. Se movió la lógica de generación de calificaciones a un método separado llamado generarCalificaciones(), para mejorar la legibilidad y separar las responsabilidades.
4. Se cambió los nombres de las variables curso1 y materia1 a curso y materia, respectivamente, para que sean más claros y concisos.
 */