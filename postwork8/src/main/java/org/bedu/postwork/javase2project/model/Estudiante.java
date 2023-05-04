package org.bedu.postwork.javase2project.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "estudiantes")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_completo")
    @Size(max = 45)
    private String nombreCompleto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudiante that = (Estudiante) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nombreCompleto, that.nombreCompleto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreCompleto);
    }
}
/*
1. Se modificó la condición en el método equals() para usar getClass() en lugar de instanceof.
    Esto garantiza que las subclases de Estudiante no sean iguales a las instancias de Estudiante,
    lo cual puede ser útil si se espera que haya subclases en el futuro. Si se desea que las
    subclases sean iguales a las instancias de Estudiante, entonces el uso de instanceof sería más apropiado.
2. Se reemplazó las llamadas a equals() en el método equals() por llamadas a Objects.equals() para manejar correctamente los objetos null.
 */