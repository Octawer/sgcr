package regulador;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import common.ID;

public interface ServicioAutenticacionInterface extends Remote{
	
	public int getAutenticacion(String name, int tipo) throws RemoteException;
	
	public boolean checkID(int id) throws RemoteException;
	
	public void listClients() throws RemoteException;
	
	public Collection<String> listDistris() throws RemoteException;
}
