package classes.ui.consola;

/**
 *
 * @author Sebastián
 */
import classes.ui.consola.utils.EntradaTecladoUtils;
import classes.excepciones.*;
import classes.logica.UniversidadFacade;

public class CursosCLI {
    
    private static final UniversidadFacade facade;

    static {
        facade = new UniversidadFacade();
    }

    public static void menuCursos() {
        String opcion;
        do {
            System.out.println("\n\n");
            System.out.println("========================================================");
            System.out.println(" Gestión de Cursos");
            System.out.println("--------------------------------------------------------");
            System.out.println("1. Listado de cursos");
            System.out.println("2. Consultar un curso por ID");
            System.out.println("3. Agregar un curso");
            System.out.println("4. Modificar un curso");
            System.out.println("5. Eliminar un curso");
            System.out.println("0. Salir");
            System.out.println("========================================================");
            opcion = EntradaTecladoUtils.obtenerOpcion("1,2,3,4,5,0".split(","));
            switch (opcion) {
                case "1":
                    listarCursos();
                    break;
                case "2":
                    consultarCurso();
                    break;
                case "3":
                    agregarCurso();
                    break;
                case "4":
                    modificarCurso();
                    break;
                case "5":
                    eliminarCurso();
                    break;
            }
        } while (!opcion.equals("0"));
        System.out.println("Hasta luego!");
    }

    private static void listarCursos() {
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Listado de cursos");
        System.out.println("========================================================");
        try {
            facade.listarCursos()
                    .forEach(curso -> {
                        System.out.println(curso.toString());
                    });
        } catch (BaseDatosException ex) {
            System.err.println("Error con base de datos: " + ex.getMessage());
        } catch (NoEncontradoException ex) {
            System.err.println(ex.getMessage());
        }

        EntradaTecladoUtils.presionaParaContinuar();
    }

    private static void consultarCurso() {
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Consultar curso");
        System.out.println("========================================================");
        String valor;
        do {
            valor = EntradaTecladoUtils.obtenerCadena("Ingrese la identificación del curso: ");

            if (valor == null
                    || valor.trim().isBlank()
                    || !valor.matches("[0-9]+")) {
                System.err.println("Debe ingresar una identificación válida");
                valor = null;
            }
        } while (valor == null);

        var id = Long.valueOf(valor);
        try {
            System.out.println(facade.consultarCurso(id));
        } catch (NoEncontradoException ex) {
            System.err.println(ex.getMessage());
        }
        EntradaTecladoUtils.presionaParaContinuar();
    }

    private static void agregarCurso() {
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Agregar un curso");
        System.out.println("========================================================");
        String nombre = EntradaTecladoUtils.obtenerCadena("Ingresa por favor el nombre:");
        Integer credito = Integer.parseInt(EntradaTecladoUtils.obtenerCadena("Ingresa por favor el número de creditos:"));
        String profesor = EntradaTecladoUtils.obtenerCadena("Ingresa por favor el nombre del profesor encargado del curso:");

        try {
            var curso = facade.guardarCurso(nombre, credito, profesor);
            System.out.println("Se ha creado el curso:\n" + curso);
        } catch (BaseDatosException ex) {
            System.err.println("Error al guardar el curso: " + ex.getMessage());
        }
        EntradaTecladoUtils.presionaParaContinuar();
    }
    
    private static void modificarCurso(){
    
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Modificar curso");
        System.out.println("========================================================");
        String valor;
        
        do {
            valor = EntradaTecladoUtils.obtenerCadena("Ingrese la identificación del curso: ");

            if (valor == null
                    || valor.trim().isBlank()
                    || !valor.matches("[0-9]+")) {
                System.err.println("Debe ingresar una identificación válida");
                valor = null;
            }
        } while (valor == null);

        var id = Long.valueOf(valor);
        try {
            var curso = facade.consultarCurso(id);
            System.out.println(curso);
            
            if(curso != null){
                String nuevoNombre = EntradaTecladoUtils.obtenerCadena("Ingresa el nuevo nombre:");
                Integer nuevoCredito = Integer.parseInt(EntradaTecladoUtils.obtenerCadena("Ingresa el nuevo número de créditos:"));
                String nuevoProfesor = EntradaTecladoUtils.obtenerCadena("Ingresa el nuevo nombre del profesor:");
                
                facade.modificarCurso(nuevoNombre,nuevoCredito,nuevoProfesor,id);
            }
        } catch (NoEncontradoException ex) {
            System.err.println(ex.getMessage());
        }
        
        EntradaTecladoUtils.presionaParaContinuar();
    }
    
    private static void eliminarCurso(){
    
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Eliminar curso");
        System.out.println("========================================================");
        String valor;
        
        do {
            valor = EntradaTecladoUtils.obtenerCadena("Ingrese la identificación del curso: ");

            if (valor == null
                    || valor.trim().isBlank()
                    || !valor.matches("[0-9]+")) {
                System.err.println("Debe ingresar una identificación válida");
                valor = null;
            }
        } while (valor == null);

        var id = Long.valueOf(valor);
        try {
            var curso = facade.consultarCurso(id);
            System.out.println(curso);
            
            if(curso != null){
                facade.eliminarCurso(id);
                System.out.println("Curso eliminado!");
            }
        } catch (NoEncontradoException ex) {
            System.err.println(ex.getMessage());
        }
        
        EntradaTecladoUtils.presionaParaContinuar();
    }
    
}
