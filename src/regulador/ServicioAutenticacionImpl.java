package regulador;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import common.Utils;

public class ServicioAutenticacionImpl implements ServicioAutenticacionInterface {
	
	private int iDnumber;
	private HashMap<Integer, String> cliente_id_nameMap = new HashMap<Integer,String>();
	private HashMap<String, Integer> cliente_name_idMap = new HashMap<String,Integer>();
	private HashMap<Integer, String> distrib_id_nameMap = new HashMap<Integer,String>();
	private HashMap<String, Integer> distrib_name_idMap = new HashMap<String,Integer>();

	public ServicioAutenticacionImpl() {
		super();
	}
	
	@Override
	public int getAutenticacion(String name, int tipo) throws RemoteException {
		switch (tipo) {
		case 0:
			if(cliente_name_idMap.containsKey(name)){
				return cliente_name_idMap.get(name);
			} else {
				iDnumber = new Random().nextInt();
				cliente_name_idMap.put(name, iDnumber);
				cliente_id_nameMap.put(iDnumber, name);
				return iDnumber;
			}
		case 1:
			if(distrib_name_idMap.containsKey(name)){
				return distrib_name_idMap.get(name);
			} else {
				iDnumber = new Random().nextInt();
				distrib_name_idMap.put(name, iDnumber);
				distrib_id_nameMap.put(iDnumber, name);
				return iDnumber;
			}
		default:
			// ojo porque los random pueden ser tb enteros negativos 
			System.out.println("valores de tipo: 0 - cliente, 1 - distribuidor");
			return -1;
		}
	}

	@Override
	public boolean checkID(int id) throws RemoteException {
		return (cliente_id_nameMap.containsKey(id) || distrib_id_nameMap.containsKey(id));
	}

	@Override
	public void listClients() throws RemoteException {
		Collection<String> cl_list = cliente_id_nameMap.values();
		for (String cl : cl_list) {
			System.out.println(cl);
		}
	}

	@Override
	public Collection<String> listDistris() throws RemoteException {
		Collection<String> dis_list = distrib_id_nameMap.values();
		for (String dis : dis_list) {
			System.out.println(dis);
		}
		return dis_list;
	}
	
public static void main(String[] args) {
		
		System.out.println("intentando establecer el codebase del autenticador");
		
		// algo no funciona aqui: no deja establecer el codebase (security manager?)
		Utils.setCodebase(ServicioAutenticacionInterface.class);
		System.out.println("codebase del autenticador establecido");
		
		try {

			ServicioAutenticacionImpl autenticador = new ServicioAutenticacionImpl();
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
