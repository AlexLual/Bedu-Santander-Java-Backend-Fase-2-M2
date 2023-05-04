package org.bedu.postwork.javase2project.async;

import org.bedu.postwork.javase2project.model.Curso;
import org.bedu.postwork.javase2project.model.Estudiante;
import org.bedu.postwork.javase2project.model.Materia;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class InscripcionAlumnos {

    private static final int NUMERO_SOLICITUDES = 20;
    private static final int ESPERA_MAXIMA = 200;

    public static void main(String[] args) {
        Random aleatorio = new Random();
        ReceptorSolicitudes receptorSolicitudes = inicializarReceptorSolicitudes();

        SolicitudEstudiante[] solicitudes = crearSolicitudes(aleatorio);

        procesarSolicitudes(aleatorio, receptorSolicitudes, solicitudes);

        receptorSolicitudes.detener();
    }

    private static ReceptorSolicitudes inicializarReceptorSolicitudes() {
        ReceptorSolicitudes receptorSolicitudes = new ReceptorSolicitudes(solicitud -> {
            if (solicitud.getEstudiante() == null || solicitud.getCurso() == null) {
                System.out.println("Solicitud rechazada por datos incompletos");
            }
            System.out.println("El estudiante: [" + solicitud.getEstudiante().getNombreCompleto()
                    + "] se ha inscrito en la materia: " + solicitud.getCurso().getMateria().getNombre());
        });

        receptorSolicitudes.iniciar();
        return receptorSolicitudes;
    }

    private static SolicitudEstudiante[] crearSolicitudes(Random aleatorio) {
        Curso[] cursos = crearCursos(aleatorio);

        SolicitudEstudiante[] solicitudes = new SolicitudEstudiante[NUMERO_SOLICITUDES];
        for (int i = 0; i < NUMERO_SOLICITUDES; i++) {
            Estudiante estudiante = new Estudiante();
            estudiante.setNombreCompleto("Estudiante " + i);
            estudiante.setId((long) i);

            solicitudes[i] = new SolicitudEstudiante(estudiante, cursos[aleatorio.nextInt(cursos.length)]);
        }
        return solicitudes;
    }

    private static Curso[] crearCursos(Random aleatorio) {
        return new Curso[]{
                crearCurso(aleatorio, "CiberSeguridad", 1),
                crearCurso(aleatorio, "Programación Estructural", 2),
                crearCurso(aleatorio, "Introduccion a Desarrollo Web", 3),
                crearCurso(aleatorio, "Angular Basico", 4)
        };
    }

    private static Curso crearCurso(Random aleatorio, String nombreMateria, long idCurso) {
        Curso curso = new Curso();
        curso.setId(idCurso);
        Materia materia = new Materia();
        materia.setNombre(nombreMateria);
        curso.setMateria(materia);
        return curso;
    }

    private static void procesarSolicitudes(Random aleatorio, ReceptorSolicitudes receptorSolicitudes, SolicitudEstudiante[] solicitudes) {
        for (SolicitudEstudiante solicitud : solicitudes) {
            receptorSolicitudes.registrarEvento(solicitud);
            try {
                TimeUnit.MILLISECONDS.sleep(aleatorio.nextInt(ESPERA_MAXIMA));
            } catch (InterruptedException error) {
                error.printStackTrace();
            }
        }
    }
}
/*
1. Se extrajeron bloques de código en el método main a métodos más pequeños y descriptivos, siguiendo el principio de responsabilidad única.
    Esto incluye la creación de los siguientes métodos:
    -inicializarReceptorSolicitudes(): inicializa el objeto ReceptorSolicitudes y comienza su ejecución.
    -crearSolicitudes(Random aleatorio): crea un array de objetos SolicitudEstudiante para su procesamiento.
    -crearCursos(Random aleatorio): crea un array de objetos Curso que se utilizarán para las solicitudes.
    -procesarSolicitudes(Random aleatorio, ReceptorSolicitudes receptorSolicitudes, SolicitudEstudiante[] solicitudes):
        procesa las solicitudes de estudiantes y las registra en el ReceptorSolicitudes.
2. Se agregaron constantes NUMERO_SOLICITUDES y ESPERA_MAXIMA para reemplazar los números mágicos en el código.
 */
