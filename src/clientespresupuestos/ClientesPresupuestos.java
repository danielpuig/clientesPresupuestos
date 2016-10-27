package clientespresupuestos;

import tools.*;

/**
 *
 * @author danielpuig
 */
public class ClientesPresupuestos {

    private static ListaClientes clientes;

    private static Fichero miFichero;

    public static void main(String[] args) {

        miFichero = new Fichero("clientesPresupuestos.xml");

        clientes = (ListaClientes) miFichero.leer();

        if (clientes == null) {
            clientes = new ListaClientes();
        }
        
        Cliente b = new Cliente("d", "d", "5", true, null);
        
        clientes.altaCliente(b);

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

    private static void crearPresupuesto() {

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
        ListaPresupuestos lista = new ListaPresupuestos();

        Cliente c = new Cliente(nombre, apellidos, telefono, vip, lista);

        if (comprobarCliente(telefono)) {
            clientes.altaCliente(c);

            miFichero.grabar(clientes);
        } else {
            System.out.println("Ya existe un cliente con ese numero de telefono.");
        }

    }

    private static boolean comprobarCliente(String telf) {
        boolean valid = true;
        if (clientes.getLista() != null) {
            for (Cliente clienteActual : clientes.getLista()) {
                if (clienteActual.getTelefono().equals(telf)) {
                    valid = false;
                    break;
                }
            }
        }
        return valid;
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
