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
    public static void main(String[] args) {
        Random rnd = new Random();
        ExecutorService pool = Executors.newCachedThreadPool();
        Curso[] cursos = new Curso[]{
                crearCurso(rnd, "CiberSeguridad", 1),
                crearCurso(rnd, "Programación Estructural", 2),
                crearCurso(rnd, "Introduccion a Desarrollo Web", 3),
                crearCurso(rnd, "Angular Basico", 4)
        };
        for(Curso curso : cursos){
            pool.execute(new CalculadorPromedioCurso(curso));
        }
        pool.shutdown();
    }

    private static Curso crearCurso(Random rnd, String nombreMateria, long idCurso) {
        Curso curso1 = new Curso();
        curso1.setId(idCurso);
        Materia materia1 = new Materia();
        materia1.setNombre(nombreMateria);
        curso1.setMateria(materia1);

        Map<Estudiante, Integer> calificaciones = new HashMap<>();
        for (int i = 1; i < 21; i++) {
            Estudiante estudiante = new Estudiante();
            estudiante.setId((long)i);
            estudiante.setNombreCompleto("Estudiante " + i);
            System.out.println(estudiante.getNombreCompleto());
            calificaciones.put(estudiante, rnd.nextInt(10));

        }

        curso1.setCalificaciones(calificaciones);
        return curso1;
    }
}
