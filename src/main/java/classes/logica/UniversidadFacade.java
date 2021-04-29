package classes.logica;

/**
 *
 * @author Sebastián
 */
import classes.datos.dao.*;
import classes.datos.entidades.*;
import classes.excepciones.*;
import java.util.List;

public class UniversidadFacade {

    private final EstudianteDao estudianteDao;
    private final CursoDao cursoDao;

    public UniversidadFacade() {
        estudianteDao = EstudianteDao.getInstance();
        cursoDao = CursoDao.getInstance();
    }

    //Estudiantes
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
    
    //Cursos
    public List<Curso> listarCursos() throws BaseDatosException, NoEncontradoException {
        var listado = cursoDao.listar();

        if (listado == null || listado.isEmpty()) {
            throw new NoEncontradoException("No existen cursos en la base de datos");
        }

        return listado;
    }

    public Curso consultarCurso(Long id) throws NoEncontradoException {
        try {
            return cursoDao.consultar(id);
        } catch (BaseDatosException ex) {
            throw new NoEncontradoException("No existe el curso con identificacion " + id);
        }
    }

    public Curso guardarCurso(String nombre, Integer creditos, String profesor) throws BaseDatosException {
        return cursoDao.guardar(nombre, creditos, profesor);
    }
    
    public void modificarCurso(String nombre, Integer creditos,
                                    String profesor, Long id) throws NoEncontradoException{
        try{
            cursoDao.modificar(nombre, creditos, profesor, id);
        } catch (BaseDatosException ex){
            throw new NoEncontradoException("No existe el curso con identificacion " + id);
        }
    }
    
    public void eliminarCurso(Long id) throws NoEncontradoException{
        try{
            cursoDao.eliminar(id);
        } catch(BaseDatosException ex){
            throw new NoEncontradoException("No existe el curso con identificacion " + id);
        }
    }

}
