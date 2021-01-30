package common;

import java.io.Serializable;


/**
 * Clase que encapsula la información de una oferta concreta
 * 
 * @author Octavio Martínez García
 * @email o.martinez.gar@gmail.com
 */

public class Oferta implements Serializable{
	
	/**
	 * VER SI ES NECESARIO HACERLA REMOTA POR POSIBLES FALLOS EN SERV_MERCS Y EL PASO
	 * DE OBJETOS REMOTOS/SERIALIZABLES
	 */
	private static final long serialVersionUID = 1496479494762190865L;
	private int kilos;
	private double precio;
	private TProducto tipo;
	
	public Oferta () 
	{
		kilos = 0;
		precio = 0.0;
		tipo = null;
	}
	
	public Oferta (int kilos, double precio, TProducto tipo)
	{
		this.kilos = kilos;
		this.precio = precio;
		this.tipo = tipo;
	}
	
	// getters
	
	public int getKilos()
	{
		return kilos;
	}
	
	public double getPrecio()
	{
		return precio;
	}
	
	public TProducto getTipo()
	{
		return tipo;
	}
	
	// setters
	
	public void setKilos( int k ) 
	{
		kilos = k;
	}
	
	public void setPrecio( double p )
	{
		precio = p;
	}
	
	public void setTipo( TProducto t )
	{
		tipo = t;
	}
	

}
