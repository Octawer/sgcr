package regulador;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import common.Oferta;
import common.TProducto;

public interface ServicioMercanciasInterface extends Remote{
	
	public void registrarOf(int id, Oferta of) throws RemoteException;
	
	public void registrarDem(int id, TProducto dem) throws RemoteException;
	
	public List<Oferta> listarOfertas(int id, TProducto prod) throws RemoteException;
	
	public List<Oferta> listarDemandas(int id, TProducto prod) throws RemoteException;

}
