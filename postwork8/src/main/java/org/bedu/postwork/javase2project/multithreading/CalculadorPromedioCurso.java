package org.bedu.postwork.javase2project.multithreading;

import org.bedu.postwork.javase2project.model.Curso;

public class CalculadorPromedioCurso implements Runnable {

    private final Curso curso;

    public CalculadorPromedioCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public void run() {
        double promedio = calcularPromedio();
        System.out.println("Promedio del curso: " + curso.getId() + " " + curso.getMateria().getNombre() + " = " + promedio);
    }

    private double calcularPromedio() {
        double promedio = 0;
        int numAlumnos = 0;

        for (Integer calificacion : curso.getCalificaciones().values()) {
            promedio += calificacion;
            numAlumnos++;
        }

        return numAlumnos > 0 ? promedio / numAlumnos : 0;
    }
}
/*
1. Se agregó final al atributo curso, ya que no es necesario modificarlo una vez que se ha asignado en el constructor.
2. Se movió la lógica de cálculo del promedio a un método privado llamado calcularPromedio(), para mejorar la legibilidad y separar las responsabilidades.
3. Se cambió la variable i a calificacion para tener un nombre más descriptivo y fácil de entender.
4. Se agregó una verificación en el cálculo del promedio para evitar la división por cero si numAlumnos es igual a 0.

 */