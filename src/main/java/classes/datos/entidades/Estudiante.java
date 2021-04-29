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
@Table(name = "Student")
public class Estudiante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FNAME")
    private String nombres;

    @Column(name = "LNAME")
    private String apellidos;

    @Column(name = "CONTACT_NO")
    private String telefono;

    public Estudiante() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Estudiante{" 
                + "\n\tid=" + id 
                + "\n, \tnombres=" + nombres 
                + "\n, \tapellidos=" + apellidos 
                + "\n, \ttelefono=" + telefono 
                + "\n}";
    }

}