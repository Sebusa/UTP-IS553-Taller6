package classes.datos.dao;

/**
 *
 * @author Sebastián
 */
import classes.datos.entidades.Curso;
import classes.excepciones.BaseDatosException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CursoDao {
    private static CursoDao instancia;

    public static CursoDao getInstance() {
        if (instancia == null) {
            instancia = new CursoDao();
        }
        return instancia;
    }
    
    private final EntityManagerFactory emf;

    private CursoDao(){
        emf = Persistence.createEntityManagerFactory("taller6-pu");
    }

    public Curso guardar(String nombre, Integer creditos, String profesor) throws BaseDatosException {
        var em = emf.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();

            Curso curso = new Curso();
            curso.setNombre(nombre);
            curso.setCreditos(creditos);
            curso.setProfesor(profesor);

            em.persist(curso);
            et.commit();

            return curso;
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            throw new BaseDatosException(ex.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Curso> listar() throws BaseDatosException {
        var em = emf.createEntityManager();
        var query = em.createQuery("select e from Curso e", Curso.class);

        try {
            return query.getResultList();
        } catch (Exception ex) {
            throw new BaseDatosException(ex.getMessage());
        } finally {
            em.close();
        }
    }

    public Curso consultar(Long id) throws BaseDatosException {
        var em = emf.createEntityManager();
        Curso estudiante = null;
        try {
            var query = em.createQuery("select e from Curso e where e.id = :id", Curso.class);
            query.setParameter("id", id);
            
            estudiante = query.getSingleResult();
        } catch (Exception ex) {
            throw new BaseDatosException(ex.getMessage());
        } finally {
            em.close();
        }
        return estudiante;
    }
    
    public void modificar(String nombre,Integer creditos, 
                                String profesor, Long id) throws BaseDatosException{
        var em = emf.createEntityManager();
        EntityTransaction et = null;
        try{
            et = em.getTransaction();
            et.begin();
            
            var curso = em.find(Curso.class, id);
            curso.setNombre(nombre);
            curso.setCreditos(creditos);
            curso.setProfesor(profesor);
            
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
            
            var curso = em.find(Curso.class, id);
            em.remove(curso);
            em.getTransaction().commit();
            
        } catch(Exception ex){
            throw new BaseDatosException(ex.getMessage());
        }
        finally{
            em.close();
        }
    }
}