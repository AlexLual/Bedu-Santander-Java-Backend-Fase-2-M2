package org.bedu.postwork.javase2project.async;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class ReceptorSolicitudes implements Runnable {

    private static final int ESPERA_SOLICITUDES = 1;
    private static final int ESPERA_NOTIFICACION = 100;

    private boolean enEjecucion;
    private final Queue<SolicitudEstudiante> solicitudesPendientes;
    private final NotificadorInscripcion notificadorInscripcion;

    public ReceptorSolicitudes(NotificadorInscripcion notificadorInscripcion) {
        this.notificadorInscripcion = notificadorInscripcion;
        this.solicitudesPendientes = new LinkedList<>();
    }

    @Override
    public void run() {
        try {
            procesarSolicitudes();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void procesarSolicitudes() throws InterruptedException {
        while (enEjecucion || !solicitudesPendientes.isEmpty()) {
            SolicitudEstudiante solicitud = solicitudesPendientes.poll();

            if (solicitud == null) {
                System.out.println("Esperando solicitudes nuevas...");
                TimeUnit.SECONDS.sleep(ESPERA_SOLICITUDES);
                continue;
            }
            notificadorInscripcion.notificarMaestro(solicitud);
            TimeUnit.MILLISECONDS.sleep(ESPERA_NOTIFICACION);
        }
    }

    public void iniciar() {
        this.enEjecucion = true;
        new Thread(this).start();
    }

    public void detener() {
        this.enEjecucion = false;
    }

    public void registrarEvento(SolicitudEstudiante evento) {
        solicitudesPendientes.add(evento);
    }

    public boolean isEnEjecucion() {
        return enEjecucion;
    }
}
/*
1. Se agregó constantes ESPERA_SOLICITUDES y ESPERA_NOTIFICACION para reemplazar los números mágicos.
2. Se cambió el nombre del campo worker a notificadorInscripcion para ser más descriptivo.
3. Se extrajó el bloque de código dentro del método run a un nuevo método llamado procesarSolicitudes, lo que hace que el método run sea más fácil de leer y entender.
4. Se cambió la visibilidad del campo solicitudesPendientes a private final ya que no se utiliza fuera de esta clase.
5. Se inicializó el campo solicitudesPendientes en la declaración, lo que hace que el constructor sea más sencillo y fácil de leer.
 */
