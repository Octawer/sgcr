package regulador;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import common.Oferta;
import common.TProducto;
import common.Utils;

public class ServicioMercanciasImpl implements ServicioMercanciasInterface {
	
	private HashMap<Integer, List<Oferta>> id_ofertas = new HashMap<Integer, List<Oferta>>();
	private HashMap<Integer, Set<TProducto>> id_demandas = new HashMap<Integer, Set<TProducto>>();

	public ServicioMercanciasImpl() {
		super();
	}

	@Override
	public void registrarOf(int id, Oferta of) throws RemoteException {
		if(id_ofertas.containsKey(id)) {
			List<Oferta> ofs = id_ofertas.get(id);
			if(ofs ==  null){
				System.out.println("lista de ofertas nula, creamos una");
				ofs = new LinkedList<Oferta>();
				imprProductos(ofs);
			}
			System.out.println("ofertas antes de añadir");
			imprProductos(ofs);
			ofs.add(of);
			System.out.println("ofertas despues de añadir");
			imprProductos(ofs);
			id_ofertas.put(id, ofs);
		} else {
			List<Oferta> ofs = new LinkedList<Oferta>();
			ofs.add(of);
			System.out.println("ofertas despues de añadir");
			imprProductos(ofs);
			id_ofertas.put(id, ofs);
		}
		
	}

	@Override
	public void registrarDem(int id, TProducto dem) throws RemoteException {
		System.out.println("empezando registro de dem");
		if(id_demandas.containsKey(id)){
			Set<TProducto> dems = id_demandas.get(id);
			if(dems.isEmpty() || !dems.contains(dem)){
				dems.add(dem);
				System.out.println("demandas despues de añadir");
				imprProductos(dems);
				id_demandas.put(id, dems);
			} else {		// ya contenia la demanda planteada
				System.out.println("demanda " + dem  + " ya en lista");
				// PONER METODOS TOSTRING EN ENUMS (TPROD, OFERTA, ETC) para debug
			}
		} else {
			System.out.println("demanda nueva");
			Set<TProducto> dems = new HashSet<TProducto>();
			dems.add(dem);
			imprProductos(dems);
			id_demandas.put(id, dems);
			System.out.println("demandas añadidas, gracias");
		}
		
	}

	@Override
	public List<Oferta> listarOfertas(int id, TProducto prod)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Oferta> listarDemandas(int id, TProducto prod)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private <E> void imprProductos(Collection<E> prods){
		for(E p : prods){
			System.out.println(p);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("intentando establecer el codebase del serv Mercs");
		
		// algo no funciona aqui: no deja establecer el codebase (security manager?)
		Utils.setCodebase(ServicioMercanciasInterface.class);
		System.out.println("codebase del serv Mercs establecido");
		
		try {

			ServicioMercanciasImpl s_mercancias = new ServicioMercanciasImpl();
			ServicioMercanciasInterface remoteMercs = (ServicioMercanciasInterface) UnicastRemoteObject.exportObject(s_mercancias, 0);
			System.out.println("Serv mercs exportado");
			
			// ver si asignamos el registry a una vble local mejor, para poder operar sobre ella luego
			Registry registro = LocateRegistry.getRegistry();
			System.out.println("Registro obtenido");
			
			// POR LO VISTO DA ERROR AL ACCEDER A LA CLASE
			// algo relacionado con la vble CLASSPATH o con las policies del Security Manager
			registro.rebind("ServMercs", remoteMercs);
			System.out.println("Serv Mercs bindeado");
		
			System.out.println("Serv Mercs establecido con éxito, presione Enter");
			//System.in.read();
			
			//registro.unbind("Autenticador");
			//UnicastRemoteObject.unexportObject(autenticador, true);
		
		} catch (Exception e) {
			System.err.println("Serv Mercs exception");
			e.printStackTrace();
		}
	}

}
