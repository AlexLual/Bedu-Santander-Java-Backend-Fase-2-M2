package org.bedu.postwork.javase2project.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "materias")
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    @Size(max = 45)
    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Materia materia = (Materia) o;
        return Objects.equals(id, materia.id) &&
                Objects.equals(nombre, materia.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
}
/*
1. Se modificó la condición en el método equals() para usar getClass() en lugar de instanceof.
    Esto garantiza que las subclases de Materia no sean iguales a las instancias de Materia,
    lo cual puede ser útil si se espera que haya subclases en el futuro. Si se desea que las subclases
    sean iguales a las instancias de Materia, entonces el uso de instanceof sería más apropiado.
2. Se reemplazó las llamadas a equals() en el método equals() por llamadas a Objects.equals() para manejar correctamente los objetos null.
 */
