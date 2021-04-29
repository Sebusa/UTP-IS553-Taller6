package classes.datos.entidades;

/**
 *
 * @author Sebastián
 */
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Subject")
public class Curso implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "NAME")
    private String nombre;
    
    @Column(name = "CREDIT")
    private Integer creditos;
    
    @Column(name = "TEACHER")
    private String profesor;
    
    public Curso(){   
    }
    
    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return this.id;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
     public String getNombre() {
        return nombre;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }
      public Integer getCreditos() {
        return creditos;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
    public String getProfesor() {
        return profesor;
    }

    @Override
    public String toString() {
        return "Curso{" 
                + "\n\t->id: " + id 
                + "\n\t->Nombre: " + nombre 
                + "\n\t->Número de créditos: " + creditos 
                + "\n\t->Profesor: " + profesor
                + "\n}";
    }
}
