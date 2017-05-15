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
    private static String[] estadoPresupuesto = {"aceptado","pendiente","rechazado"};

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
                    altaPresupuesto();
                    break;
                case 3:
                    getPresupuestoByEstado("pendiente");
                    break;
                case 4:
                    getPresupuestoByTelf();
                    break;
                case 5:
                    getPresupuestoByEstado("rechazado");
                    break;
                case 6:
                    mostrarClientes();
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
    
    public static void altaPresupuesto(){
        String clienteNum = cadenaNoVacia("Introduce el número del cliente");
        boolean exists = clientes.clienteExists(clienteNum);
        
        Cliente cliente = clientes.getByNum(clienteNum);
        
        if(exists){
            boolean existsNumPresupuesto;
            int numPresupues=0;
            do{
            numPresupues = tools.InputData.pedirEntero("Introduce el número del presupuesto");
            existsNumPresupuesto = clientes.existsPresupuesto(numPresupues);
            if(existsNumPresupuesto){
                System.out.println("Ya existe un presupuesto registrado bajo ese número");
            }
            }while(existsNumPresupuesto);
            
            String concepto = cadenaNoVacia("Introduce el concepto");
            double presupuestoNeto = tools.InputData.pedirDouble("Introduce el total del presupuesto");
            boolean valid = false;
            boolean pendiente = false;
            String estado;
            
            do{
                estado = cadenaNoVacia("Estado del presupuesto: (aceptado/pendiente/rechazado)");
                for(String x:estadoPresupuesto){
                    if(x.equalsIgnoreCase(estado)){
                        valid = true;
                    }
                }
                if(!valid){
                    
                    pendiente = preguntar("No ha colocado un estado válido. ¿Dejar como pendiente?");
                    if(pendiente){
                        estado = "pendiente";
                    }
                }
            }while(!valid && !pendiente);
            
            Presupuesto presupuesto = new Presupuesto(numPresupues, concepto, presupuestoNeto, estado);
            cliente.getLista().addPresupuesto(presupuesto);
            ficheroClientes.grabar(clientes);
            
        }else{
            boolean createNewCliente;
            createNewCliente = preguntar("El cliente no existe, desea darlo de alta?");
            if(createNewCliente){
                System.out.println("Redirigiendo a alta de nuevo cliente...");
                altaCliente();
                
            }else{
                
            }
        }
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
    
    @SuppressWarnings("empty-statement")
    public static String stringNoVacio(String mensaje){
        String getString;
        do{
            getString = tools.InputData.pedirCadena(mensaje);
            if(getString.equals("")){
                System.out.println("Por favor, introduce un valor valido");
            };
        }while(getString.equals(""));
        
        return getString;
    }
    
    public static boolean preguntar(String mensaje){
        String q;
        do{   
        q = cadenaNoVacia(mensaje + " (si/no)");
        if(!q.equalsIgnoreCase("si")&&!q.equalsIgnoreCase("no")){
            System.out.println("Por favor, introduce un valor valido");
        }
        }while(!q.equalsIgnoreCase("si")&&!q.equalsIgnoreCase("no"));
        return q.equals("si");
    }

    private static void getPresupuestoByEstado(String estado) {
        System.out.println("\tLista de presupuestos por estado: "+estado);
        ArrayList<Presupuesto> listaPresupuestosPendientes;
        for(Cliente clienteActual: clientes.getLista()){
            System.out.println("Presupuestos del cliente: "+clienteActual.getNombre()+" "+clienteActual.getApellidos());
            
            listaPresupuestosPendientes = clienteActual.getLista().getPresupuestoByEstado(estado);
            if(!listaPresupuestosPendientes.isEmpty()){
                for(Presupuesto presupuestoActual: listaPresupuestosPendientes){
                    System.out.println("Nº:\t\t\t"+presupuestoActual.getNumPres());
                    System.out.println("Concepto:\t\t"+presupuestoActual.getConcepto());
                    System.out.println("Presupuesto neto:\t"+presupuestoActual.getPrecioFinal()+"€");
                    System.out.println("Presupuesto final:\t"+presupuestoActual.calcPrecioFinal(clienteActual)+"€");
                }
                System.out.println("---------------------");
                
            }else{
                System.out.println("La lista de presupuestos de este cliente está vacía según el criterio \""+estado+"\"");
            }
        }
    }

    private static void getPresupuestoByTelf() {
        String clienteNum = stringNoVacio("Introduce el telefono del cliente");
        boolean exists = clientes.clienteExists(clienteNum);
        Cliente cliente = clientes.getByNum(clienteNum);
        if(exists){
        System.out.println("Presupuestos del cliente: "+cliente.getNombre()+" "+cliente.getApellidos());
            if(!cliente.getLista().getLista().isEmpty()){
                for(Presupuesto presupuestoActual: cliente.getLista().getLista()){
                    System.out.println("Nº:\t\t\t"+presupuestoActual.getNumPres());
                    System.out.println("Concepto:\t\t"+presupuestoActual.getConcepto());
                    System.out.println("Presupuesto neto:\t"+presupuestoActual.getPrecioFinal()+"€");
                    System.out.println("Presupuesto final:\t"+presupuestoActual.calcPrecioFinal(cliente)+"€");
                }
            }else{
                System.out.println("La lista de presupuestos del cliente: "+cliente.getNombre()+" "+cliente.getApellidos()+" está vacía");
            }
        }else{
            System.out.println("El cliente no existe.");
        }
    }

    private static void mostrarClientes() {
        for(Cliente clienteActual: clientes.getLista()){
            System.out.println("Nombre:\t\t\t"+clienteActual.getNombre());
            System.out.println("Apellido:\t\t"+clienteActual.getApellidos());
            System.out.println("Telefono:\t\t"+clienteActual.getTelefono());
            String vipString = clienteActual.isVip() ? "Sí" : "No";
            System.out.println("VIP:\t\t\t"+vipString);
            int totalPresupuestos=0;
            for(Presupuesto countPresupuesto: clienteActual.getLista().getLista()){
                totalPresupuestos++;
            }
            System.out.println("Nº de presupuestos:\t"+totalPresupuestos);
            System.out.println("-------------------------------------");
        }
    }

}
