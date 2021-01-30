package common;



public class ID {
	
	
	// numero de serializacion
	private int id;
	private String name;
	private TipoActor tipo;
	
	public ID(){
	}
	
	public ID (int i, String n, int t){
		if (t == 0) {
			tipo = TipoActor.CLIENTE;
		} else if (t == 1) {
			tipo = TipoActor.DISTRIBUIDOR;
		}
		id = i;
		name = n;
	}
	
	public int getID(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public TipoActor getTipo(){
		return tipo;
	}

}
