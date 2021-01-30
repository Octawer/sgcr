package distribuidor;

import common.Oferta;

public interface DistribuidorInterface {
	
	public void introducirOferta(Oferta of);
	
	// ver objeto ventas (lista de ofertas?? List || HashMap ? VER
	// ver si usamos java.swing para GUI (si hay tiempo, VER ALF)
	public void mostrarVentas(Ventas vs);
	
	// devuelve un registry id o cualqueir otro id para indicar exito o fracaso?
	public int salir();
	
	// tiene que almacenar en una BD (o en una list o hashMap) las mecancias que
	// quiera registrar en la BD del regulador ya que las compras se buscan en
	// el regulador PERO SE REALIZAN EN EL DISTRIBUIDOR, de modo que
	// el stock se deduce de su propio inventario (list/hash/BD)
	
	// asimismo tb debemos actualizar (limpiar) tanto las listas de Regulador como
	// las de Distribuidor
	
	public void comprarProducto();
	
	
	

}
