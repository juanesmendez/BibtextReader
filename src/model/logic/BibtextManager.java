package model.logic;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import api.IBibtextManager;;

public class BibtextManager implements IBibtextManager{
	
	private LectorArchivo lector;
	
	public BibtextManager() {
		this.lector = new LectorArchivo();
	}
	@Override
	public boolean leerArchivo(String fileName) throws IOException {
		// TODO Auto-generated method stub
		
		/*
		FileReader fileReader=null;
		try {
			fileReader= new FileReader(fileName);
			BufferedReader br = new BufferedReader(fileReader);
			String line;
		    while ((line = br.readLine()) != null) {
		        System.out.println(line);
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			fileReader.close();
		}*/
	    //LectorArchivo lector = new LectorArchivo();
		
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
