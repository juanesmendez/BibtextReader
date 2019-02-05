package model.logic;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import api.IBibtextManager;;

public class BibtextManager implements IBibtextManager{
	
	private LectorArchivo lector;
	
	public BibtextManager() {
	}
	
	public boolean leerArchivo(String fileName) throws IOException {
		this.lector = new LectorArchivo();
		return lector.read(fileName);
	}
	
	public void mostrarBibliografiasValidas() {
		lector.printBibliographyTypes();
	}
	
	public void mostrarAnalisisCamposReqYOpc() {
		lector.printRequiredAndOptionalAnalysis();
	}
	
	public void mostrarErrores() {
		lector.printFormatErrors();
	}
}
