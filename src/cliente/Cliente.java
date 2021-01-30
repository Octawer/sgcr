package cliente;

import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import regulador.ReguladorInterface;
import regulador.ServicioAutenticacionInterface;
import regulador.ServicioMercanciasInterface;
import common.Gui;
import common.Oferta;
import common.ID;
import common.TProducto;

public class Cliente {
	
	// Si para autenticar (dos listas) pasamos el tipo de objeto (clase) como param
	// tendremos que declarar esta clase como serializable, ya que los unicos objetos
	// que se pueden pasar en RMI son REMOTOS, SERIALIZABLES O PREDEFINIDOS (int, float ...)

	private static int id;

	// EN PPIO (UNIT_TEST) NO INSTANCIAREMOS ESTA CLASE
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		//Registry registry = LocateRegistry.getRegistry();
		//try {
			//ReguladorInterface regu = (ReguladorInterface) registry.lookup("Regulador");
			// pruebas del Serv_Aut
			//ServicioAutenticacionInterface sai = (ServicioAutenticacionInterface) registry.lookup("Autenticador");
			/**
			String nombre;
	        Scanner sc = new Scanner(new InputStreamReader(System.in));
	        System.out.println("Como te llamas?");
	        nombre = sc.next();
			//System.out.println(regu.autenticarse(nombre, 0));
			//System.out.println(regu.autenticarse(nombre, 0));
	        System.out.println(sai.getAutenticacion(nombre, 0));
	        System.out.println(sai.getAutenticacion(nombre, 0));
	        System.out.println(sai.getAutenticacion("Pepe", 0));
	        System.out.println(sai.getAutenticacion("Ozyx", 0));
	        id = sai.getAutenticacion("Ozyx", 0);
	        sai.listClients();
	        */
	        // pruebas del Serv_Mercs
	        //ServicioMercanciasInterface smi = (ServicioMercanciasInterface) registry.lookup("ServMercs");
	        /**
	        smi.registrarDem(id, TProducto.SOJA);
	        smi.registrarDem(id, TProducto.LENTEJAS);
	        smi.registrarDem(id, TProducto.SOJA);
	        System.out.println("efectuada dem " + TProducto.SOJA + " con el id " + id);
	        */
		//} catch (NotBoundException e) {
		//	e.printStackTrace();
		//}
		String[] opciones =  {"Introducir Demanda", "Recibir Oferta", "Comprar Mercs", "Salir"};
        int op = Gui.menu("Cliente", opciones);
        switch (op) {
		case 0:
			System.out.println("has elegido la opcion 0, enhorabuena ... pero no hace nada");
			break;
		case 1:
			System.out.println("has elegido la opcion 1, enhorabuena ... pero no hace nada");
			break;
		case 2:
			System.out.println("has elegido la opcion 2, enhorabuena ... pero no hace nada");
			break;
		case 3:
			System.out.println("has elegido la opcion 3, enhorabuena ... pero no hace nada");
			break;
		default:
			System.out.println("has elegido la opcion erronea, da igual ... porque no hace nada");
			break;
        }
		

	}

	public void introducirDemanda() {
		// TODO Auto-generated method stub
		
	}

	public Oferta recibirOferta() {
		// TODO Auto-generated method stub
		return null;
	}

	public void comprarMercancia(Oferta of) {
		// TODO Auto-generated method stub
		
	}

	public void salir() {
		// TODO Auto-generated method stub
		
	}

}
