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
                    altaPresupuesto();
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
    
    public static void altaPresupuesto(){
        String clienteNum = cadenaNoVacia("Introduce el número del cliente");
        boolean exists = clientes.clienteExists(clienteNum);
        
        Cliente cliente = clientes.getByNum(clienteNum);
        
        if(exists){
            //System.out.println("Alta pre");
            boolean existsNumPresupuesto;
            int numPresupues=0;
            do{
            //------------------------- reemplazar --------------------
            //Para buscar directamente en el fichero cliente
            // 
            /*
            for(Cliente asd:listaClientes.getLista()){
                for(Presupuesto prep: asd.getListaPresupuestos().getLista()){
                    //check si existe ese numero de presupuesto y gestinar...
                    //De esta forma se evita duplicar
                }
            }
            */
            numPresupues = tools.InputData.pedirEntero("Introduce el número del presupuesto");
            existsNumPresupuesto = clientes.existsPresupuesto(numPresupues);
            if(existsNumPresupuesto){
                System.out.println("Ya existe un presupuesto registrado bajo ese número");
            }
            }while(existsNumPresupuesto);
            
            String concepto = cadenaNoVacia("Introduce el concepto");
            double presupuestoNeto = tools.InputData.pedirDouble("Introduce el monto neto del presupuesto");
            boolean validPresupuesto = false;
            boolean isPendiente = false;
            String estado;
            
            do{
                estado = cadenaNoVacia("Estado del presupuesto: [aceptado] [pendiente] [rechazado]");
                for(String x:estadoPresupuesto){
                    if(x.equalsIgnoreCase(estado)){
                        validPresupuesto = true;
                    }
                }
                if(!validPresupuesto){
                    
                    isPendiente = preguntar("No ha colocado un estado válido. ¿Dejar como pendiente?");
                    if(isPendiente){
                        estado = "pendiente";
                    }
                }
            }while(!validPresupuesto && !isPendiente);
            
            //TODO !!!
            //No está dando de alta los presupuestos
            //Revisar
            //El problema era que actualizaba sólamente el fichero que no debería crearse, 
            //en lugar de actualizar las listas desde los cliente
            
            Presupuesto presupuesto = new Presupuesto(numPresupues, concepto, presupuestoNeto, estado);
            //Es innecesario agregar a la lista de presupuestos del fichero duplicado...
            //listaPresupuestos.addPresupuesto(presupuesto);
            //cliente.setListaPresupuestos(listaPresupuestos);
            cliente.getLista().addPresupuesto(presupuesto);
            ficheroClientes.grabar(clientes);
            //No hace falta grabar este fichero
            //ficheroPresupuestos.grabar(listaPresupuestos);
            
        }else{
            boolean createNewCliente;
            createNewCliente = preguntar("El cliente no existe, desea darlo de alta?");
            if(createNewCliente){
                System.out.println("Redirigiendo a alta de nuevo cliente...");
                altaCliente();
                
            }else{
                //...
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
    
    public static boolean preguntar(String mensaje){
        String aws;
        do{   
        aws = cadenaNoVacia(mensaje+" [si][no]");
        if(!aws.equalsIgnoreCase("si")&&!aws.equalsIgnoreCase("no")){
            System.out.println("Por favor, introduce un valor valido");
        }
        }while(!aws.equalsIgnoreCase("si")&&!aws.equalsIgnoreCase("no"));
        return aws.equals("si");
    }

}
