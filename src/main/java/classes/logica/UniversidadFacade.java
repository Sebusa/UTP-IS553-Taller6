package classes.logica;

/**
 *
 * @author Sebastián
 */
import classes.datos.dao.EstudianteDao;
import classes.datos.entidades.Estudiante;
import classes.excepciones.*;
import java.util.List;

public class UniversidadFacade {

    private final EstudianteDao estudianteDao;

    public UniversidadFacade() {
        estudianteDao = EstudianteDao.getInstance();
    }

    public List<Estudiante> listarEstudiantes() throws BaseDatosException, NoEncontradoException {
        var listado = estudianteDao.listar();

        if (listado == null || listado.isEmpty()) {
            throw new NoEncontradoException("No existen estudiates en la base de datos");
        }

        return listado;
    }

    public Estudiante consultarEstudiante(Long id) throws NoEncontradoException {
        try {
            return estudianteDao.consultar(id);
        } catch (BaseDatosException ex) {
            throw new NoEncontradoException("No existe el estudiante con identificacion " + id);
        }
    }

    public Estudiante guardarEstudiante(String nombre, String apellido, String telefono) throws BaseDatosException {
        return estudianteDao.guardar(nombre, apellido, telefono);
    }
    
    public void modificarEstudiante(String nombre, String appellido,
                                    String telefono, Long id) throws NoEncontradoException{
        try{
            estudianteDao.modificar(nombre, appellido, telefono, id);
        } catch (BaseDatosException ex){
            throw new NoEncontradoException("No existe el estudiante con identificacion " + id);
        }
    }
    
    public void eliminarEstudiante(Long id) throws NoEncontradoException{
        try{
            estudianteDao.eliminar(id);
        } catch(BaseDatosException ex){
            throw new NoEncontradoException("No existe el estudiante con identificacion " + id);
        }
    }

}
