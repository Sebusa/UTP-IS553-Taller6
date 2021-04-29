package classes.ui.consola;

/**
 *
 * @author Sebastián
 */
import classes.ui.consola.utils.EntradaTecladoUtils;

public class Consola {
    
     public static void iniciar() {
        menuPrincipal();
    }

    private static void menuPrincipal() {
        String opcion;
        do {
            System.out.println("\n\n");
            System.out.println("========================================================");
            System.out.println(" Bienvenidos a la Gestion Universitaria");
            System.out.println("--------------------------------------------------------");
            System.out.println("1. Gestion de estudiantes");
            System.out.println("0. Salir");
            System.out.println("========================================================");
            opcion = EntradaTecladoUtils.obtenerOpcion("1,0".split(","));
            switch (opcion) {
                case "1":
                    EstudiantesCLI.menuEstudiantes();
                    break;
            }
        } while (!opcion.equals("0"));

        System.out.println("Gracias por usar nuestra aplicación");
    }
    
}
