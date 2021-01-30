package regulador;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import common.Oferta;
import common.TProducto;

/**
 * Interfaz remota del regulador de la actividad comercial 
 * DEBE SER UN OBJETO RMI SERVER ??
 * 
 * @author Octavio Martínez García
 * @email o.martinez.gar@gmail.com
 */

public interface ReguladorInterface extends Remote {
	
	
	// el objeto que la implemente debe inicialmente levantar estos 2 servicios
	
	// servicio de Autenticacion -  devuelve al objeto llamador (cliente o distri) un id
	// entero con el cual se le autoriza a realizar las operaciones
	//public int levantarAutenticacion(String name) throws RemoteException;
	
	
	// servicio de Mercancias
	// ESTE METODO DEBE SER VOID (QUIZAS SOLO CREARSE EN EL CONSTRUCTOR EL OBJETO)
	//public int levantarServMercs() throws RemoteException;
	
	
	public int autenticarse(String name, int tipo) throws RemoteException;
	
	public void registrarOfertas(int distId, List<Oferta> ofertas) throws RemoteException;
	
	public void registrarDemanda(int clientId, TProducto mercancia) throws RemoteException;
	
	public void listarDemandas(int id, TProducto mercs) throws RemoteException;
	
	public void listarOfertas(int id, TProducto mercs) throws RemoteException;
	
	public void listarClientes(int id) throws RemoteException;
		
	public void listarDistribuidores(int id) throws RemoteException;
	
	//public boolean buscarOferta(int clientId, TProducto demanda) throws RemoteException;
	
	// los metodos de listar (listarClientes, listarDistr, listarOfertas, etc.)
	// deben ser locales o remotos? es decir se invocaran desde el propio Regulador
	// o desde los clientes/distribuidores?? VER ESTO --> segun parece deben ser remotos 
	// https://cursosvirtuales.uned.es/dotlrn/grados/asignaturas/71013029-13/uforums/thread-view?message_id=48677785

	// callback o devolución de llamada (avisar al cliente de ofertas) vs "listar ofertas"
	// en ppio hacerlo con "listar" y si hay tiempo extender a callback
	
	
	// void o lista de Ofertas (es decir, solo lo muestra por consola, o tb
	// se le pasa la lista de productos ?? VER ESTO
	// se usaria el "id" si fuera remoto, ya que si solo fuese local no seria
	// necesario, porque lo invocaria el propio regulador
	//public void listarOfertas(int id, TProducto mercs) throws RemoteException;
	
	
}
