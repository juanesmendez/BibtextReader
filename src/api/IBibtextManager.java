package api;

import java.io.IOException;

public interface IBibtextManager {
	public boolean leerArchivo(String fileName) throws IOException ;
	
	public void mostrarBibliografiasValidas();
	
	public void mostrarAnalisisCamposReqYOpc();
	
	public void mostrarErrores();
}
