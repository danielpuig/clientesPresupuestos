package clientespresupuestos;

import java.util.ArrayList;
import tools.*;

/**
 *
 * @author danielpuig
 */
public class ClientesPresupuestos {

    private static ListaClientes clientes;
    private static ListaPresupuestos presupuestos;
    private static Fichero ficheroClientes;
    private static Fichero ficheroPresupuestos;

    public static void main(String[] args) {
        
        //---------------------------------
        
        ficheroClientes = new Fichero("ficheroClientes.xml");
        ficheroPresupuestos = new Fichero("ficheroPresupuestos.xml");
        
        clientes = (ListaClientes) ficheroClientes.leer();
        presupuestos = (ListaPresupuestos) ficheroPresupuestos.leer();
        
        if(clientes==null){
            clientes = new ListaClientes();
        }
        
        if(presupuestos==null){
            presupuestos = new ListaPresupuestos();
        }
        
        //---------------------------------

        int opc;
        do {
            mostrarMenu();
            opc = InputData.pedirEntero("Escoge una opcion: ");
            switch (opc) {
                case 1:
                    altaCliente();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    System.out.println("Hasta la próxima!");
                    break;
                default:
                    System.out.println("Opción incorrecta.");
            }
        } while (opc != 8);

    }

    private static void mostrarMenu() {
        System.out.println("**** MENU ****");
        System.out.println("1. Alta cliente");
        System.out.println("2. Nuevo presupuesto");
        System.out.println("3. Presupuestos pendientes");
        System.out.println("4. Listado de presupuestos de un cliente");
        System.out.println("5. Listado de presupuestos rechazados");
        System.out.println("6. Listado de clientes");
        System.out.println("7. Cambiar estado de un presupuesto");
        System.out.println("8. Salir");
    }

    private static void altaCliente() {
        String nombre = cadenaNoVacia("Nombre: ");
        String apellidos = cadenaNoVacia("Apellidos: ");
        String telefono = cadenaNoVacia("Telefono: ");
        String respuesta;
        boolean vip = false;
        do {
            respuesta = cadenaNoVacia("Es un cliente VIP? (si/no)?");
            if (respuesta.equalsIgnoreCase("SI")) {
                vip = true;
            } else if (respuesta.equalsIgnoreCase("NO")) {
                vip = false;
            } else {
                System.out.println("Debes responder SI o NO");
            }
        } while (!respuesta.equalsIgnoreCase("SI") && !respuesta.equalsIgnoreCase("NO"));

        Cliente c = new Cliente(nombre, apellidos, telefono, vip);
        
        clientes.altaCliente(c);
        ficheroClientes.grabar(clientes);

    }
    
    private static void altaPresupuesto() {

    }
    
    private static String cadenaNoVacia(String msg) {
        String cadena;
        do {
            cadena = InputData.pedirCadena(msg);
            if (cadena.equals("")) {
                System.out.println("no se puede dejar en blanco");
            }
        } while (cadena.equals(""));
        return cadena;
    }

}
