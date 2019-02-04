package Controller;

import java.io.IOException;

import api.IBibtextManager;
import model.logic.BibtextManager;
import view.Interfaz;

public class Controller {
	private static IBibtextManager manager = new BibtextManager();
	
	public static boolean leerArchivo(String fileName) throws IOException {
		return manager.leerArchivo(fileName);
	}
	
	public static void mostrarTiposBibliografiasValidas() {
		manager.mostrarBibliografiasValidas();
	}
	
	public static void mostrarAnalisisCamposReqYOpc() {
		manager.mostrarAnalisisCamposReqYOpc();
	}
	
	public static void mostrarErrores() {
		manager.mostrarErrores();
	}
}
