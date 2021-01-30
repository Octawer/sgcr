package common;

public class Utils {
	
	public static final String CODEBASE = "java.rmi.server.codebase";
	
	public static void setCodebase(Class<?> c)
	{
		System.out.println("obteniendo ruta de clase");
		String rutaDeClase = c.getProtectionDomain().getCodeSource().getLocation().toString();
		System.out.println("ruta de clase obtenida ok");
		
		String previousPath = System.getProperty(CODEBASE);
		
		if (previousPath != null && !previousPath.isEmpty()){
			rutaDeClase = previousPath + " " + rutaDeClase;
		}
		
		System.setProperty(CODEBASE, rutaDeClase);
	}

}
