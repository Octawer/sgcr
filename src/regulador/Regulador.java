package regulador;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import common.Oferta;
import common.TProducto;
import common.Utils;


/**
 * Clase que representa al regulador de la actividad comercial 
 * DEBE SER UN OBJETO RMI SERVER ??
 * 
 * @author Octavio Martínez García
 * @email o.martinez.gar@gmail.com
 */

public class Regulador implements ReguladorInterface{
	
	private ServicioAutenticacionInterface autenticador;
	private ServicioMercanciasInterface servMercancias;
	// test
	private List<String> names = new ArrayList<String>();

	// es necesario declarar que lanza una remote excep en el constructor? (pag 186)
	public Regulador() throws RemoteException {
		
		// NOTA: SON OBJETOS REMOTOS, HAY QUE EXPORTARLOS O SE PUEDEN CREAR ASI ??
		// ES DECIR SE INVOCARAN DE FORMA REMOTA O LOCAL?? VER ALF
		// constructor remoto
		Registry registry = LocateRegistry.getRegistry();
		try {
			autenticador = (ServicioAutenticacionInterface) registry.lookup("Autenticador");
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		//constructor normal (local)
		//ServicioAutenticacionInterface autenticador = new ServicioAutenticacionImpl();
		ServicioMercanciasInterface servMercancias = new ServicioMercanciasImpl();
		
	}

	/**
	 * DEVUELVE CADA VEZ UN Nº ID DIFERENTE (PORQUE NO MANTIENE UNA LISTA IDS
	 * PERMANENTE DEBIDO AL CONSTRUCTOR ??)
	 */
	@Override
	public int autenticarse(String name, int tipo) throws RemoteException {
		return autenticador.getAutenticacion(name, tipo);
	}
	
	/**
	 * STUB TEST DE AUTENTICACION
	 */
	public int autenticarseTest(String name, int tipo) throws RemoteException {
		//return autenticador.getAutenticacion(name, tipo);
		if(names.isEmpty() || !names.contains(name)){
			System.out.println(name + " not in names");
			names.add(name);
			return names.indexOf(name);
		} else {
			System.out.println(name + " is in names");
			return names.indexOf(name);
		}
	}

	@Override
	public void registrarOfertas(int distId, List<Oferta> ofertas)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarDemanda(int clientId, TProducto mercancia)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listarDemandas(int id, TProducto mercs) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listarOfertas(int id, TProducto mercs) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listarClientes(int id) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listarDistribuidores(int id) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	public static void main (String[] args) throws Exception
	{
		// establecer un Security Manager antes para seguridad en accesos a rutas
		// desde donde se descargan los codigos de implementacion de las clases
		
		System.out.println("intentando establecer el codebase");
		
		// algo no funciona aqui: no deja establecer el codebase (security manager?)
		Utils.setCodebase(ReguladorInterface.class);
		System.out.println("codebase establecido");
		
		// ver si inlcuimos el Security (en el tutorial de javaRMI lo hacen...)
		//if(System.getSecurityManager() == null) {
		//	System.setSecurityManager(new SecurityManager());
		//}
		try {
		
			// iniciar el proceso rmiregistry - EN EL SCRIPT (BASH O EL QUE SEA)

			
			// deberia iniciar los main de sus 2 servicios (Aut, mercs) 
			// para levantar dichos servicios antes de buscarles en el registry
			// SE SUPONE QUE sAUT Y sMERCS DEPENDEN DE REGULADOR, DE MODO QUE
			// LES DEBE INI EL, POR TANTO DEBE MANTENER VISIBILIDAD DE ELLOS (NO?)
			// levantamos el ServAut
			ServicioAutenticacionImpl.main(null);
			// levantamos el ServMercs
			ServicioMercanciasImpl.main(null);
			
			Regulador regulador = new Regulador();
			ReguladorInterface remoteRegula = (ReguladorInterface)UnicastRemoteObject.exportObject(regulador, 0);
			System.out.println("Regulador exportado");
			
			// ver si asignamos el registry a una vble local mejor, para poder operar sobre ella luego
			Registry registro = LocateRegistry.getRegistry();
			System.out.println("Registro obtenido");
			
			// POR LO VISTO DA ERROR AL ACCEDER A LA CLASE
			// algo relacionado con la vble CLASSPATH o con las policies del Security Manager
			registro.rebind("Regulador", remoteRegula);
			System.out.println("Registro bindeado");
		
			System.out.println("Regulador establecido con éxito, presione Enter para cerrar");
			System.in.read();
			
			registro.unbind("Regulador");
			UnicastRemoteObject.unexportObject(regulador, true);
		
		} catch (Exception e) {
			System.err.println("Regulador exception");
			e.printStackTrace();
		}
		
	}


}
