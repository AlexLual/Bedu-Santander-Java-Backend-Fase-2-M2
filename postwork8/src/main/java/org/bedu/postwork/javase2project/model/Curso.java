package org.bedu.postwork.javase2project.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "cursos")
public class Curso {
    public static final Integer NO_CALIFICADO = -100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ciclo")
    @Size(max = 4)
    private String ciclo;

    @ManyToOne
    @JoinColumn(name = "materias_fk", referencedColumnName = "id")
    private Materia materia;

    @ElementCollection
    @CollectionTable(name = "cursos_has_estudiantes", joinColumns = {@JoinColumn(name = "cursos_fk", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "estudiantes_fk", referencedColumnName = "id")
    @Column(name = "calificacion")
    private Map<Estudiante, Integer> calificaciones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Map<Estudiante, Integer> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(Map<Estudiante, Integer> calificaciones) {
        this.calificaciones = calificaciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return id.equals(curso.id) &&
                Objects.equals(ciclo, curso.ciclo) &&
                Objects.equals(materia, curso.materia) &&
                Objects.equals(calificaciones, curso.calificaciones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ciclo, materia, calificaciones);
    }
}
/*
1. Se modificó la condición en el método equals() para usar getClass() en lugar de instanceof.
    Esto garantiza que las subclases de Curso no sean iguales a las instancias de Curso, lo cual puede ser útil
    si se espera que haya subclases en el futuro. Si se desea que las subclases sean iguales a las instancias de Curso,
    entonces el uso de instanceof sería más apropiado.
2. Se reemplazó las llamadas a equals() en el método equals() por llamadas a Objects.equals() para manejar correctamente los objetos null.
 */