package common;

import java.io.Serializable;

public interface OfertaInterface extends Serializable{
	
	public int getKilos();
	
	public double getPrecio();
	
	public TProducto getTipo();

}
