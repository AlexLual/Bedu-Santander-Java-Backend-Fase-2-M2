package org.bedu.postwork.javase2project.reactive;

import org.bedu.postwork.javase2project.model.Curso;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CalcularPromedioCursoRx {

    public Mono<Double> calcularPromedio(Curso curso) {
        return Mono.fromCallable(() -> curso.getCalificaciones().values().stream()
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(0.0));
    }
}
/*
1. Se reemplazó el uso de Flux.fromStream() y parallelStream() con Mono.fromCallable(), ya que sólo se necesita un valor único (el promedio) y no una secuencia de valores.
2. Se simplificó el flujo de datos utilizando el método mapToDouble() en lugar de un Collectors.averagingDouble(), y llamé directamente al método average() en el flujo de datos.
3. Se agregó el uso de orElse(0.0) al final de la cadena de operaciones, para proporcionar un valor por defecto en caso de que no haya calificaciones en el curso.
 */
