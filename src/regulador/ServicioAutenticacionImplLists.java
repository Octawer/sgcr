package regulador;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Random;

import common.ID;
import common.TipoActor;
import common.Utils;

public class ServicioAutenticacionImplLists implements ServicioAutenticacionInterface {
	
	// ver si este campo (atributo de clase) lo dejamos asi o lo hacemos
	// no static ( --> necesitaria inicializarse en el constructor entonces...)
	private int iDnumber;
	
	// mas eficiente la busqueda con hashMaps, pero mejor diseño con una clase aparte
	// para los ID´s (ver .asta) (habria que pasar objetos serializables (ID) y no ints ...
	
	private ID id;
	private List<ID> idList;
	private List<String> names = new ArrayList<String>();
	

	public ServicioAutenticacionImplLists() {
		id = new ID();
		idList = new ArrayList<ID>();
	}

	/**
	 * busca en la lista de ID´s devolviendo el numero de id que corresponde al nombre
	 * de cliente o distribuidor dado, o crea un ID nuevo, le incluye en la lista de ID´s 
	 * y devuelve ese nº de id nuevo
	 * @param name El nombre del cliente o distribuior
	 * @param tipo 0 si es cliente / 1 si es distribuidor (VER SI PASAMOS CLASS EN VEZ DE ESTO)
	 */
	@Override
	public int getAutenticacion(String name, int tipo) throws RemoteException {	
		if (idList.isEmpty() || !hasName(name) ){
			System.out.println(name + " no esta en lista de ID´s");
			id = new ID(diceIDnumber(),name,tipo);
			idList.add(id);
			printList();
			return id.getID();
		} else {
			id = searchIDList(name);
			System.out.println(name + " esta en lista de ID´s");
			printList();
			return id.getID();
		}
		/**
		if(names.isEmpty() || !names.contains(name)){
			System.out.println(name + " not in names");
			names.add(name);
			return names.indexOf(name);
		} else {
			System.out.println(name + " is in names");
			return names.indexOf(name);
		}
		*/
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean checkID(int i) throws RemoteException {
		Iterator<ID> iter = idList.iterator();
		// buscamos el nº de id en la lista y devolvemos true si esta
		while(iter.hasNext()){
			ID searchID = iter.next();
			if(searchID.getID() == i){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Devuelve una lista de todos los nombres de clientes registrados
	 * VER SI ESTO SE IMPLEMENTA POR SESIONES O SE MANTIENE LA AUTENTICACION (REGSITRO)
	 * PARA MAS DE UNA SESION...
	 */
	@Override
	public List<String> listClients() throws RemoteException {
		if (idList.isEmpty()) {
			return null;
		} else {
			List<String> listaNombres = new ArrayList<String>();
			Iterator<ID> iter = idList.iterator();
			while (iter.hasNext()){
				ID searchID = iter.next();
				if(searchID.getTipo() == TipoActor.CLIENTE){
					listaNombres.add(searchID.getName());
				}
			}
			return listaNombres;
		}
	}

	/**
	 * Devuelve una lista de todos los nombres de distribuidores registrados
	 * VER SI ESTO SE IMPLEMENTA POR SESIONES O SE MANTIENE LA AUTENTICACION (REGSITRO)
	 * PARA MAS DE UNA SESION...
	 */
	@Override
	public List<String> listDistris() throws RemoteException {
		if (idList.isEmpty()) {
			return null;
		} else {
			List<String> listaNombres = new ArrayList<String>();
			Iterator<ID> iter = idList.iterator();
			while (iter.hasNext()){
				ID searchID = iter.next();
				if(searchID.getTipo() == TipoActor.DISTRIBUIDOR){
					listaNombres.add(searchID.getName());
				}
			}
			return listaNombres;
		}
	}
	
	
	private int diceIDnumber() 
	{
		iDnumber = new Random().nextInt();
		return iDnumber;
	}
	
	/**
	 * Busca en la lista de ID´s el nº de id correspondiente al nombre
	 * @param name El nombre del cliente o distribuidor
	 * @return El objeto ID que corresponde a ese nombre, si existe, o null si no
	 */
	private ID searchIDList(String name){
		Iterator<ID> iter = idList.iterator();
		// buscamos el nombre en la lista y devolvemos su id si esta
		while(iter.hasNext()){
			ID searchID = iter.next();
			if(searchID.getName() == name){
				return searchID;
			}
		}
		return null;
	}
	
	/**
	 * Busca en la lista de ID´s el nº de id correspondiente al nombre
	 * @param name El nombre del cliente o distribuidor
	 * @return El objeto ID que corresponde a ese nombre, si existe, o null si no
	 */
	private boolean hasName(String name){
		Iterator<ID> iter = idList.iterator();
		// buscamos el nombre en la lista y devolvemos su id si esta
		while(iter.hasNext()){
			if(iter.next().getName() == name){
				return true;
			}
		}
		return false;
	}
	
	private void printList(){
		Iterator<ID> iter = idList.iterator();
		// buscamos el nombre en la lista y devolvemos su id si esta
		while(iter.hasNext()){
			ID searchID = iter.next();
			System.out.println(searchID.getName());
		}
	}
	
	
	/**
	 * QUIZAS ESTO DEBERIA HACERSE EN EL CONSTRUCTOR PARA QUE EL REGULADOR PUEDA INVOCARLO
	 * BIEN DESDE EL MAIN O DESDE SU CONSTRUCTOR (VER ESTO)
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("intentando establecer el codebase del autenticador");
		
		// algo no funciona aqui: no deja establecer el codebase (security manager?)
		Utils.setCodebase(ServicioAutenticacionInterface.class);
		System.out.println("codebase del autenticador establecido");
		
		try {

			ServicioAutenticacionImplLists autenticador = new ServicioAutenticacionImplLists();
			ServicioAutenticacionInterface remoteAut = (ServicioAutenticacionInterface)UnicastRemoteObject.exportObject(autenticador, 0);
			System.out.println("Regulador exportado");
			
			// ver si asignamos el registry a una vble local mejor, para poder operar sobre ella luego
			Registry registro = LocateRegistry.getRegistry();
			System.out.println("Registro obtenido");
			
			// POR LO VISTO DA ERROR AL ACCEDER A LA CLASE
			// algo relacionado con la vble CLASSPATH o con las policies del Security Manager
			registro.rebind("Autenticador", remoteAut);
			System.out.println("Registro bindeado");
		
			System.out.println("Autenticador establecido con éxito, presione Enter");
			//System.in.read();
			
			//registro.unbind("Autenticador");
			//UnicastRemoteObject.unexportObject(autenticador, true);
		
		} catch (Exception e) {
			System.err.println("Autenticador exception");
			e.printStackTrace();
		}

	}


}
