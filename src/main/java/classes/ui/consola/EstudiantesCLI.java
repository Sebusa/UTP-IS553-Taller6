package classes.ui.consola;

/**
 *
 * @author Sebastián
 */
import classes.ui.consola.utils.EntradaTecladoUtils;
import classes.excepciones.*;
import classes.logica.UniversidadFacade;

public class EstudiantesCLI {
    
    private static final UniversidadFacade facade;

    static {
        facade = new UniversidadFacade();
    }

    public static void menuEstudiantes() {
        String opcion;
        do {
            System.out.println("\n\n");
            System.out.println("========================================================");
            System.out.println(" Gestión de Estudiantes");
            System.out.println("--------------------------------------------------------");
            System.out.println("1. Listado de estudiantes");
            System.out.println("2. Consultar un estudiante por ID");
            System.out.println("3. Agregar un estudiante");
            System.out.println("4. Modificar un estudiante");
            System.out.println("5. Eliminar un estudiante");
            System.out.println("0. Salir");
            System.out.println("========================================================");
            opcion = EntradaTecladoUtils.obtenerOpcion("1,2,3,4,5,0".split(","));
            switch (opcion) {
                case "1":
                    listarEstudiantes();
                    break;
                case "2":
                    consultarEstudiante();
                    break;
                case "3":
                    agregarEstudiante();
                    break;
                case "4":
                    modificarEstudiante();
                    break;
                case "5":
                    eliminarEstudiante();
                    break;
            }
        } while (!opcion.equals("0"));
        System.out.println("Hasta luego!");
    }

    private static void listarEstudiantes() {
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Listado de estudiantes");
        System.out.println("========================================================");
        try {
            facade.listarEstudiantes()
                    .forEach(estudiante -> {
                        System.out.println(estudiante.toString());
                    });
        } catch (BaseDatosException ex) {
            System.err.println("Error con base de datos: " + ex.getMessage());
        } catch (NoEncontradoException ex) {
            System.err.println(ex.getMessage());
        }

        EntradaTecladoUtils.presionaParaContinuar();
    }

    private static void consultarEstudiante() {
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Consultar estudiante");
        System.out.println("========================================================");
        String valor;
        do {
            valor = EntradaTecladoUtils.obtenerCadena("Ingrese la identificación del estudiante: ");

            if (valor == null
                    || valor.trim().isBlank()
                    || !valor.matches("[0-9]+")) {
                System.err.println("Debe ingresar una identificación válida");
                valor = null;
            }
        } while (valor == null);

        var id = Long.valueOf(valor);
        try {
            System.out.println(facade.consultarEstudiante(id));
        } catch (NoEncontradoException ex) {
            System.err.println(ex.getMessage());
        }
        EntradaTecladoUtils.presionaParaContinuar();
    }

    private static void agregarEstudiante() {
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Agregar un estudiante");
        System.out.println("========================================================");
        String nombre = EntradaTecladoUtils.obtenerCadena("Ingresa por favor los nombres:");
        String apellido = EntradaTecladoUtils.obtenerCadena("Ingresa por favor los apellidos:");
        String telefono = EntradaTecladoUtils.obtenerCadena("Ingresa por favor el número de contacto:");

        try {
            var estudiante = facade.guardarEstudiante(nombre, apellido, telefono);
            System.out.println("Se ha creado el estudiante:\n" + estudiante);
        } catch (BaseDatosException ex) {
            System.err.println("Error al guardar el estudiante: " + ex.getMessage());
        }
        EntradaTecladoUtils.presionaParaContinuar();
    }
    
    private static void modificarEstudiante(){
    
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Modificar estudiante");
        System.out.println("========================================================");
        String valor;
        
        do {
            valor = EntradaTecladoUtils.obtenerCadena("Ingrese la identificación del estudiante: ");

            if (valor == null
                    || valor.trim().isBlank()
                    || !valor.matches("[0-9]+")) {
                System.err.println("Debe ingresar una identificación válida");
                valor = null;
            }
        } while (valor == null);

        var id = Long.valueOf(valor);
        try {
            var estudiante = facade.consultarEstudiante(id);
            System.out.println(estudiante);
            
            if(estudiante != null){
                String nuevoNombre = EntradaTecladoUtils.obtenerCadena("Ingresa los nuevos nombres:");
                String nuevoApellido = EntradaTecladoUtils.obtenerCadena("Ingresa los nuevos apellidos:");
                String nuevoTelefono = EntradaTecladoUtils.obtenerCadena("Ingresa el nuevo teléfono:");
                
                facade.modificarEstudiante(nuevoNombre,nuevoApellido,nuevoTelefono,id);
            }
        } catch (NoEncontradoException ex) {
            System.err.println(ex.getMessage());
        }
        
        EntradaTecladoUtils.presionaParaContinuar();
    }
    
    private static void eliminarEstudiante(){
    
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Eliminar estudiante");
        System.out.println("========================================================");
        String valor;
        
        do {
            valor = EntradaTecladoUtils.obtenerCadena("Ingrese la identificación del estudiante: ");

            if (valor == null
                    || valor.trim().isBlank()
                    || !valor.matches("[0-9]+")) {
                System.err.println("Debe ingresar una identificación válida");
                valor = null;
            }
        } while (valor == null);

        var id = Long.valueOf(valor);
        try {
            var estudiante = facade.consultarEstudiante(id);
            System.out.println(estudiante);
            
            if(estudiante != null){
                facade.eliminarEstudiante(id);
                System.out.println("Estudiante eliminado!");
            }
        } catch (NoEncontradoException ex) {
            System.err.println(ex.getMessage());
        }
        
        EntradaTecladoUtils.presionaParaContinuar();
    }
}
