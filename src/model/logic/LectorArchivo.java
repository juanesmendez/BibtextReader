package model.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;


import model.world.Article;
import model.world.Bibliography;
import model.world.Book;
import model.world.Booklet;
import model.world.Conference;
import model.world.Inbook;
import model.world.Incollection;
import model.world.Inproceedings;
import model.world.Manual;
import model.world.MastersThesis;
import model.world.Misc;
import model.world.PhdThesis;
import model.world.Proceedings;
import model.world.TechReport;
import model.world.Unpublished;

public class LectorArchivo {

	private int erroresFormato;
	private int erroresId;
	private int erroresCorchetes;
	private int erroresComas;

	private int articles;
	private int books;
	private int booklets;
	private int conferences;
	private int inbooks;
	private int incollections;
	private int inproceedings;
	private int manuals;
	private int mastersThesis;
	private int miscs;
	private int phdThesis;
	private int proceedings;
	private int techReports;
	private int unPublished;

	private String requiredAndOptionalAnalysis;

	List<Bibliography> bibliographiesInfo;
	
	private int contEntries;
	private int contValidEntries;
	public LectorArchivo() {
		this.erroresFormato = 0;
		this.erroresId = 0;
		this.erroresCorchetes = 0;
		this.erroresComas = 0;
		this.articles = 0;
		this.books = 0;
		this.conferences = 0;
		this.inbooks = 0;
		this.incollections = 0;
		this.inproceedings = 0;
		this.manuals = 0;
		this.mastersThesis = 0;
		this.miscs = 0;
		this.phdThesis = 0;
		this.proceedings = 0;
		this.techReports = 0;
		this.unPublished = 0;
		this.requiredAndOptionalAnalysis = "ANALISIS CAMPOS REQUERIDOS Y OPCIONALES: \n\n";
		this.bibliographiesInfo = new ArrayList<>();
		this.contEntries = 0;
		this.contValidEntries = 0;
	}
	public boolean read(String filePath) throws IOException {


		FileReader fileReader = null;
		BufferedReader buffer = null;
		try {
			fileReader= new FileReader(filePath);
			buffer = new BufferedReader(fileReader);
			String line;
			String info;
			String key;
			String cadena;
			String toValidate; //Cadena que contiene el string de la información de cada entrada biliográfica para validar si el sintaxis es correcto
			while( (line = buffer.readLine()) != null) {
				System.out.println(line);
				//Aca empieza la lógica
				if(line.startsWith("@")) {
					String array[] = line.split("\\{");
					String type = array[0].substring(1);
					System.out.println("TIPO: "+ type);
					//System.out.println("pos 0: " + array[0]);
					//Meter esto en un metodo
					if(array.length < 2) {
						//System.out.println("Existe un error con el formato");
						erroresFormato++;
						erroresId++;
					}
					
					//-----------------------------------
					toValidate = line;
					cadena = "";

					info = buffer.readLine();
					while( !info.isEmpty() && info.length() != 1) {
						//fields.add((info.split("="))[0].toLowerCase().trim());
						System.out.println(info);
						toValidate += info;
						cadena += info;

						info = buffer.readLine();
						if(info == null) {
							break;
						}
					}
					toValidate += info;
					System.out.println("TO VALIDATE: " + toValidate);
					boolean valid = validateEntry(toValidate);
					//Añadir condicional cuando si sea valida la entrada.
					
					if(valid == true) {
						countBibliographyTypes(type);
						checkType(type, cadena);
					}
					
				}
			}
			/*
			printFormatErrors();
			printBibliographyTypes();
			printRequiredAndOptionalAnalysis();
	*/

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}finally {
			buffer.close();
			fileReader.close();

		}
		return true;
	}


	private boolean validateEntry(String toValidate) {
		boolean valid = true;
		int contCorcheteIzq = 0;
		int contCorcheteDer = 0;
		int contSignoIgual = 0;
		int contComas = 0;
		
		contEntries ++;
		for(int i=0; i<toValidate.length();i++) {	
			if(toValidate.charAt(i) == '{') {
				contCorcheteIzq++;
			}else if(toValidate.charAt(i) == '}') {
				contCorcheteDer++;
			}else if(toValidate.charAt(i) == ',' && toValidate.charAt(i-1) == '}') {
				contComas++;
			}else if(toValidate.charAt(i) == '=') {
				contSignoIgual++;
			}
		}
		//----------------------------------------------------------------
		//Desde aqui agregare una idea que tengo
		String[] array = toValidate.split("=");
		System.out.println();
		System.out.println();
		for(int i=0;i<array.length;i++) {
			System.out.println("Posicion " + i + ": " + array[i]);
		}
		System.out.println();
		System.out.println();
		
		
		//------------------------------------------------------------------------
		System.out.println();
		System.out.println("Corchetes izquierda: " + contCorcheteIzq);
		System.out.println("Corchetes derecha: " + contCorcheteDer);
		System.out.println("Comas: " + contComas);
		System.out.println("Signo igual: " + contSignoIgual);
		System.out.println();
		if((contCorcheteIzq != contCorcheteDer) ) {
			valid = false;
			erroresFormato++;
			erroresCorchetes++;
		}else if(contSignoIgual - contComas != 1){
			valid = false;
			erroresFormato++;
			erroresComas++;
		}else {
			contValidEntries++;
		}
		
		return valid;
	}
	private void checkType(String type, String info) {


		switch (type) {

		case "article":

			Article article = new Article(info);
			bibliographiesInfo.add(article);
			article.checkFields(type);
			article.buildCheckFieldString();
			requiredAndOptionalAnalysis += article.getFieldCheckInfo();

			break;

		case "book":
			Book book = new Book(info);
			bibliographiesInfo.add(book);
			book.checkFields(type);
			book.buildCheckFieldString();
			requiredAndOptionalAnalysis += book.getFieldCheckInfo();


			break;

		case "booklet":

			Booklet booklet = new Booklet(info);
			bibliographiesInfo.add(booklet);
			booklet.checkFields(type);
			booklet.buildCheckFieldString();
			requiredAndOptionalAnalysis += booklet.getFieldCheckInfo();

			break;

		case "conference":

			Conference conference = new Conference(info);
			bibliographiesInfo.add(conference);
			conference.checkFields(type);
			conference.buildCheckFieldString();
			requiredAndOptionalAnalysis += conference.getFieldCheckInfo();

			break;
		case "inbook":

			Inbook inbook = new Inbook(info);
			bibliographiesInfo.add(inbook);
			inbook.checkFields(type);
			inbook.buildCheckFieldString();
			requiredAndOptionalAnalysis += inbook.getFieldCheckInfo();

			break;
		case "incollection":

			Incollection incollection = new Incollection(info);
			bibliographiesInfo.add(incollection);
			incollection.checkFields(type);
			incollection.buildCheckFieldString();
			requiredAndOptionalAnalysis += incollection.getFieldCheckInfo();

			break;
		case "inproceedings":

			Inproceedings inproceeding = new Inproceedings(info);
			bibliographiesInfo.add(inproceeding);
			inproceeding.checkFields(type);
			inproceeding.buildCheckFieldString();
			requiredAndOptionalAnalysis += inproceeding.getFieldCheckInfo();

			break;

		case "manual":

			Manual manual= new Manual(info);
			bibliographiesInfo.add(manual);
			manual.checkFields(type);
			manual.buildCheckFieldString();
			requiredAndOptionalAnalysis += manual.getFieldCheckInfo();

			break;
		case "mastersthesis":

			MastersThesis masters = new MastersThesis(info);
			bibliographiesInfo.add(masters);
			masters.checkFields(type);
			masters.buildCheckFieldString();
			requiredAndOptionalAnalysis += masters.getFieldCheckInfo();

			break;
		case "misc":

			Misc misc = new Misc(info);
			bibliographiesInfo.add(misc);
			misc.checkFields(type);
			misc.buildCheckFieldString();
			requiredAndOptionalAnalysis += misc.getFieldCheckInfo();

			break;
		case "phdthesis":

			PhdThesis phd = new PhdThesis(info);
			bibliographiesInfo.add(phd);
			phd.checkFields(type);
			phd.buildCheckFieldString();
			requiredAndOptionalAnalysis += phd.getFieldCheckInfo();

			break;
		case "proceedings":

			Proceedings proceedings= new Proceedings(info);
			bibliographiesInfo.add(proceedings);
			proceedings.checkFields(type);
			proceedings.buildCheckFieldString();
			requiredAndOptionalAnalysis += proceedings.getFieldCheckInfo();

			break;
		case "techreport":

			TechReport techReport= new TechReport(info);
			bibliographiesInfo.add(techReport);
			techReport.checkFields(type);
			techReport.buildCheckFieldString();
			requiredAndOptionalAnalysis += techReport.getFieldCheckInfo();

			break;
		case "unpublished":

			Unpublished unpublished = new Unpublished(info);
			bibliographiesInfo.add(unpublished);
			unpublished.checkFields(type);
			unpublished.buildCheckFieldString();
			requiredAndOptionalAnalysis += unpublished.getFieldCheckInfo();

			break;

		default:
			break;
		}
	}

	public void printFormatErrors() {
		System.out.println();
		System.out.println("TOTAL DE ERRORES EN EL FORMATO DEL ARCHIVO: "+  erroresFormato);
		System.out.println();
		System.out.println("Entradas con ID faltante: " + erroresId);
		System.out.println("Errores en cierre o apertura de corchetes en entrada bibliográfica: " + erroresCorchetes);
		System.out.println("Errores por falta de comas separadoras en entrada bibliográfica: " + erroresComas);
		System.out.println();
	}

	public void printBibliographyTypes() {
		System.out.println();
		System.out.println("CONTADOR DE TIPOS DE BIBLIOGRAFIAS VALIDAS:");
		System.out.println("De las " + contEntries + " entradas bibliográficas detectadas, " + contValidEntries + " son validas.");
		System.out.println();
		System.out.println("Articles: " + articles);
		System.out.println("Books: " + books);
		System.out.println("Booklets: " + booklets);
		System.out.println("Conferences: " + conferences);
		System.out.println("Inbooks: " + inbooks);
		System.out.println("Incollections: " + incollections);
		System.out.println("Inproceedings: " + inproceedings);
		System.out.println("Manuals: " + manuals);
		System.out.println("Masters Thesis: " + mastersThesis);
		System.out.println("Miscs: " + miscs);
		System.out.println("Phd Thesis: " + phdThesis);
		System.out.println("Proceedings: " + proceedings);
		System.out.println("Tech report: " + techReports);
		System.out.println("Unpublished: " + unPublished);
		System.out.println();
	}

	public void printRequiredAndOptionalAnalysis() {
		System.out.println();
		System.out.println(this.requiredAndOptionalAnalysis);
		System.out.println();
	}

	public void countBibliographyTypes(String type) {
		switch (type) {

		case "article":
			articles++;
			break;
		case "book":
			books++;
			break;
		case "booklet":
			booklets++;
			break;
		case "conference":
			conferences++;
			break;
		case "inbook":
			inbooks++;
			break;
		case "incollection":
			incollections++;
			break;
		case "inproceedings":
			inproceedings++;
			break;

		case "manual":
			manuals++;
			break;
		case "mastersthesis":
			mastersThesis++;
			break;
		case "misc":
			miscs++;
			break;
		case "phdthesis":
			phdThesis++;
			break;
		case "proceedings":
			proceedings++;
			break;
		case "techreport":
			techReports++;
			break;
		case "unpublished":
			unPublished++;
			break;

		default:
			break;
		}
	}


}
