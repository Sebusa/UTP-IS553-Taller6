package classes.datos.dao;

import classes.datos.entidades.Estudiante;
import classes.excepciones.BaseDatosException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Sebastián
 */
public class EstudianteDao {

    private static EstudianteDao instancia;

    public static EstudianteDao getInstance() {
        if (instancia == null) {
            instancia = new EstudianteDao();
        }
        return instancia;
    }

    private final EntityManagerFactory emf;

    private EstudianteDao() {
        emf = Persistence.createEntityManagerFactory("taller6-pu");
    }

    public Estudiante guardar(String nombres, String apellidos, String telefono) throws BaseDatosException {
        var em = emf.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();

            Estudiante estudiante = new Estudiante();
            estudiante.setNombres(nombres);
            estudiante.setApellidos(apellidos);
            estudiante.setTelefono(telefono);

            em.persist(estudiante);
            et.commit();

            return estudiante;
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            throw new BaseDatosException(ex.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Estudiante> listar() throws BaseDatosException {
        var em = emf.createEntityManager();
        var query = em.createQuery("select e from Estudiante e", Estudiante.class);

        try {
            return query.getResultList();
        } catch (Exception ex) {
            throw new BaseDatosException(ex.getMessage());
        } finally {
            em.close();
        }
    }

    public Estudiante consultar(Long id) throws BaseDatosException {
        var em = emf.createEntityManager();
        Estudiante estudiante = null;
        try {
            var query = em.createQuery("select e from Estudiante e where e.id = :id", Estudiante.class);
            query.setParameter("id", id);
            
            estudiante = query.getSingleResult();
        } catch (Exception ex) {
            throw new BaseDatosException(ex.getMessage());
        } finally {
            em.close();
        }
        return estudiante;
    }
    
    public void modificar(String nombre,String apellido, 
                                String telefono, Long id) throws BaseDatosException{
        var em = emf.createEntityManager();
        EntityTransaction et = null;
        try{
            et = em.getTransaction();
            et.begin();
            
            var estudiante = em.find(Estudiante.class, id);
            estudiante.setNombres(nombre);
            estudiante.setApellidos(apellido);
            estudiante.setTelefono(telefono);
            
            em.getTransaction().commit();
            
        } catch(Exception ex){
            throw new BaseDatosException(ex.getMessage());
        }
        finally{
            em.close();
        }
    }
    
     public void eliminar(Long id) throws BaseDatosException{
        var em = emf.createEntityManager();
        EntityTransaction et = null;
        try{
            et = em.getTransaction();
            et.begin();
            
            var estudiante = em.find(Estudiante.class, id);
            em.remove(estudiante);
            em.getTransaction().commit();
            
        } catch(Exception ex){
            throw new BaseDatosException(ex.getMessage());
        }
        finally{
            em.close();
        }
    }
}
