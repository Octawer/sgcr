package regulador;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import common.Utils;

public class MainRegulador {
	
	// ver como ejecutamos el rmiregistry desde el main (creador) del server
	// en vez de invocarlo desde la consola (ya que el ED no lo hará)
	
	public static void main (String[] args) throws Exception
	{
		// establecer un Security Manager antes para seguridad en accesos a rutas
		// desde donde se descargan los codigos de implementacion de las clases
		
		System.out.println("intentando establecer el codebase");
		
		// algo no funciona aqui: no deja establecer el codebase (security manager?)
		Utils.setCodebase(ReguladorInterface.class);
		System.out.println("codebase establecido");
		
		//if(System.getSecurityManager() == null) {
		//	System.setSecurityManager(new SecurityManager());
		//}
		try {
		
			// VER porque no deja en el puerto por defecto (en 8888 por ej, si, + o - ...)
		
			// iniciar el proceso rmiregistry - VER COMO

			
		
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
		
			System.out.println("Regulador establecido con éxito, presione Enter");
			System.in.read();
			
			registro.unbind("Regulador");
			UnicastRemoteObject.unexportObject(regulador, true);
		
		} catch (Exception e) {
			System.err.println("Regulador exception");
			e.printStackTrace();
		}
		
	}
		
		
}
