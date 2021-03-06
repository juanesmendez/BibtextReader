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

	private int erroresCorchetesEnValor;
	private int erroresCapitalization;

	private int articles;
	private int totalArticles;
	private int books;
	private int totalBooks;
	private int booklets;
	private int totalBooklets;
	private int conferences;
	private int totalConferences;
	private int inbooks;
	private int totalInbooks;
	private int incollections;
	private int totalIncollections;
	private int inproceedings;
	private int totalInproceedings;
	private int manuals;
	private int totalManuals;
	private int mastersThesis;
	private int totalMastersThesis;
	private int miscs;
	private int totalMiscs;
	private int phdThesis;
	private int totalPhdThesis;
	private int proceedings;
	private int totalProceedings;
	private int techReports;
	private int totalTechReports;
	private int unPublished;
	private int totalUnPublished;

	private String requiredAndOptionalAnalysis;

	List<Bibliography> bibliographiesInfo;

	private int contEntries;
	private int contValidEntries;
	
	public LectorArchivo() {
		this.erroresFormato = 0;
		this.erroresId = 0;
		this.erroresCorchetes = 0;
		this.erroresComas = 0;
		this.erroresCorchetesEnValor = 0;
		this.erroresCapitalization = 0;
		this.articles = 0;
		this.totalArticles = 0;
		this.totalBooks = 0;
		this.totalBooklets = 0;
		this.totalConferences = 0;
		this.totalInbooks = 0;
		this.totalIncollections = 0;
		this.totalInproceedings = 0;
		this.totalManuals = 0;
		this.totalMastersThesis = 0;
		this.totalMiscs = 0;
		this.totalPhdThesis = 0;
		this.totalProceedings = 0;
		this.totalTechReports = 0;
		this.totalUnPublished = 0;
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
		this.requiredAndOptionalAnalysis = "ANALISIS CAMPOS REQUERIDOS Y OPCIONALES (Bibliografías válidas): \n\n";
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
			String cadena;
			String toValidate; //Cadena que contiene el string de la información de cada entrada biliográfica para validar si el sintaxis es correcto
			while( (line = buffer.readLine()) != null) {
				//System.out.println(line);
				//Aca empieza la lógica
				if(line.startsWith("@")) {
					String array[] = line.split("\\{");
					String type = array[0].substring(1);
					//System.out.println("TIPO: "+ type);
					toValidate = line + "\n";
					cadena = "";
					info = buffer.readLine();
					while( !info.isEmpty() && info.length() != 1) {
						System.out.println(info);
						toValidate += info;

						toValidate += "\n";


						cadena += info;

						info = buffer.readLine();
						if(info == null) {
							break;
						}
					}
					toValidate += info;
					//System.out.println("TO VALIDATE: " + toValidate);
					boolean valid = validateEntry(toValidate);
					countErrors(toValidate);
					countTotalBibliographyTypes(type);
					if(valid == true) {
						countBibliographyTypes(type);
						checkType(type, cadena);
					}
				}
			}
			System.out.println();
			System.out.println("---------------------------------------------------------------------------------------------------");
			System.out.println();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}finally {
			buffer.close();
			fileReader.close();

		}
		return true;
	}

	
	private void checkAndCountIdError(String toValidate) {
		String[] auxArray;
		auxArray = toValidate.split("\n");
		auxArray = auxArray[0].split("\\{");
		if(auxArray.length < 2) { //Compruebo que tenga el ID
			erroresId++;
			erroresFormato++;
		}
	}

	private void checkAndCountCorcheteAndComasError(String toValidate) {
		int contCorcheteIzq = 0;
		int contCorcheteDer = 0;
		int contSignoIgual = 0;
		int contComas = 0;
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
		/*
		System.out.println();
		System.out.println("Corchetes izquierda: " + contCorcheteIzq);
		System.out.println("Corchetes derecha: " + contCorcheteDer);
		System.out.println("Comas: " + contComas);
		System.out.println("Signo igual: " + contSignoIgual);
		System.out.println();*/
		if((contCorcheteIzq != contCorcheteDer)) {
			erroresCorchetes++;
			erroresFormato++;
		}
		if(contSignoIgual - contComas != 1) {
			erroresComas++;
			erroresFormato++;
		}

	}

	private void checkAndCountCapitalizationError(String toValidate) {
		toValidate = toValidate.trim();
		boolean capitalized = Character.isUpperCase(toValidate.charAt(0));
		if(!capitalized) {
			erroresCapitalization ++;
			erroresFormato++;
		}
	}

	private void countErrors(String toValidate) {
		checkAndCountIdError(toValidate);
		checkAndCountCorcheteAndComasError(toValidate);
		String[] array = toValidate.split("\n");

		for(int i=1;i<array.length;i++) { 
			String[] content = array[i].split("=");
			if(content.length > 1) {
				checkAndCountCapitalizationError(content[0]);
				if(content[1].charAt(content[1].length()-1) == ',') {
					content[1] = content[1].substring(0, content[1].length()-1); //le quito la coma del final
				}
				checkAndCountCorchetesInKeyError(content[1]);
			}
		}
	}

	private void checkAndCountCorchetesInKeyError(String stringToCheck) {
		int izquierda = 0;
		int derecha = 0;

		for(int j=0;j<stringToCheck.length();j++) {
			if(stringToCheck.charAt(j) == '{') {
				izquierda++;
			}else if(stringToCheck.charAt(j) == '}') {
				derecha++;
			}
		}
		if(stringToCheck.charAt(stringToCheck.length()-1) == '}' && stringToCheck.charAt(stringToCheck.length()-2) == '}') {
			if((izquierda != 1) || (derecha != 2)) {
				erroresCorchetesEnValor++;
				erroresCorchetes++; //Le aumento al contador de errores de corchetes en general
				erroresFormato++;
			}
		}else {
			if((izquierda != 1) || (derecha != 1)) {
				erroresCorchetesEnValor++;
				erroresCorchetes++;
				erroresFormato++;
			}
		}
	}
	private boolean validateEntry(String toValidate) {
		boolean aux;

		contEntries ++;

		aux = checkId(toValidate);//Compruebo que tenga el ID
		if(!aux) { 
			return false;
		}
		aux = checkCorcheteAndComas(toValidate);
		if(!aux) {
			return false;
		}
		//----------------------------------------------------------------
		//Desde aqui agregare una idea que tengo
		String[] array = toValidate.split("\n");

		for(int i=1;i<array.length;i++) { //Empieza en 1 por que la posicion 0 del arreglo no me interesa
			//System.out.println("Posicion " + i + ": " + array[i]);

			//Revisar que al menos tenga corchetes o sino sera invalida la entrada

			String[] content = array[i].split("=");

			if(content.length > 1) {
				//System.out.println("CONTENT[0] = " + content[0]);
				aux = checkCapitalization(content[0]);
				if(!aux) {
					return false;
				}

				if(content[1].charAt(content[1].length()-1) == ',') {
					content[1] = content[1].substring(0, content[1].length()-1); //le quito la coma del final
				}

				aux = checkCorchetesInKey(content[1]);
				if(!aux) {
					return false;
				}
				//System.out.println("CONTENT[1] = " + content[1]);
			}


		}
		/*
		System.out.println();
		System.out.println();
		 */
		contValidEntries++;

		return true;
	}

	private boolean checkCorcheteAndComas(String toValidate) {
		int contCorcheteIzq = 0;
		int contCorcheteDer = 0;
		int contSignoIgual = 0;
		int contComas = 0;
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
		/*
		System.out.println();
		System.out.println("Corchetes izquierda: " + contCorcheteIzq);
		System.out.println("Corchetes derecha: " + contCorcheteDer);
		System.out.println("Comas: " + contComas);
		System.out.println("Signo igual: " + contSignoIgual);
		System.out.println();*/
		if((contCorcheteIzq != contCorcheteDer) || (contSignoIgual - contComas != 1)) {
			return false;
		}
		return true;
	}
	private boolean checkId(String toValidate) {
		String[] auxArray;
		auxArray = toValidate.split("\n");
		auxArray = auxArray[0].split("\\{");
		if(auxArray.length < 2) { //Compruebo que tenga el ID
			return false;
		}
		return true;
	}
	private boolean checkCapitalization(String stringToCheck) {
		stringToCheck = stringToCheck.trim();
		boolean capitalized = Character.isUpperCase(stringToCheck.charAt(0));
		/*
		if(!capitalized) {
			erroresCapitalization ++;
			erroresFormato++;

		}*/
		return capitalized;
	}

	private boolean checkCorchetesInKey(String stringToCheck) {
		int izquierda = 0;
		int derecha = 0;
		boolean valid = true;

		for(int j=0;j<stringToCheck.length();j++) {
			if(stringToCheck.charAt(j) == '{') {
				izquierda++;
			}else if(stringToCheck.charAt(j) == '}') {
				derecha++;
			}
		}

		if(stringToCheck.charAt(stringToCheck.length()-1) == '}' && stringToCheck.charAt(stringToCheck.length()-2) == '}') {
			if((izquierda != 1) || (derecha != 2)) {
				valid = false;
				//System.out.println("ERROR");
				//erroresCorchetesEnValor++;
				//erroresCorchetes++; //Le aumento al contador de errores de corchetes en general
				//erroresFormato++;
			}
		}else {


			if((izquierda != 1) || (derecha != 1)) {
				valid = false;
				//System.out.println("ERROR");
				//erroresCorchetesEnValor++;
				//erroresFormato++;
			}
		}


		return valid;
	}

	private void checkType(String type, String info) {


		switch (type) {

		case "article":

			Article article = new Article(info);
			bibliographiesInfo.add(article);
			article.checkFields();
			article.buildCheckFieldString();
			requiredAndOptionalAnalysis += article.getFieldCheckInfo();

			
			
			break;

		case "book":
			Book book = new Book(info);
			bibliographiesInfo.add(book);
			book.checkFields();
			book.buildCheckFieldString();
			requiredAndOptionalAnalysis += book.getFieldCheckInfo();


			break;

		case "booklet":

			Booklet booklet = new Booklet(info);
			bibliographiesInfo.add(booklet);
			booklet.checkFields();
			booklet.buildCheckFieldString();
			requiredAndOptionalAnalysis += booklet.getFieldCheckInfo();

			break;

		case "conference":

			Conference conference = new Conference(info);
			bibliographiesInfo.add(conference);
			conference.checkFields();
			conference.buildCheckFieldString();
			requiredAndOptionalAnalysis += conference.getFieldCheckInfo();

			break;
		case "inbook":

			Inbook inbook = new Inbook(info);
			bibliographiesInfo.add(inbook);
			inbook.checkFields();
			inbook.buildCheckFieldString();
			requiredAndOptionalAnalysis += inbook.getFieldCheckInfo();

			break;
		case "incollection":

			Incollection incollection = new Incollection(info);
			bibliographiesInfo.add(incollection);
			incollection.checkFields();
			incollection.buildCheckFieldString();
			requiredAndOptionalAnalysis += incollection.getFieldCheckInfo();

			break;
		case "inproceedings":

			Inproceedings inproceeding = new Inproceedings(info);
			bibliographiesInfo.add(inproceeding);
			inproceeding.checkFields();
			inproceeding.buildCheckFieldString();
			requiredAndOptionalAnalysis += inproceeding.getFieldCheckInfo();

			break;

		case "manual":

			Manual manual= new Manual(info);
			bibliographiesInfo.add(manual);
			manual.checkFields();
			manual.buildCheckFieldString();
			requiredAndOptionalAnalysis += manual.getFieldCheckInfo();

			break;
		case "mastersthesis":

			MastersThesis masters = new MastersThesis(info);
			bibliographiesInfo.add(masters);
			masters.checkFields();
			masters.buildCheckFieldString();
			requiredAndOptionalAnalysis += masters.getFieldCheckInfo();

			break;
		case "misc":

			Misc misc = new Misc(info);
			bibliographiesInfo.add(misc);
			misc.checkFields();
			misc.buildCheckFieldString();
			requiredAndOptionalAnalysis += misc.getFieldCheckInfo();

			break;
		case "phdthesis":

			PhdThesis phd = new PhdThesis(info);
			bibliographiesInfo.add(phd);
			phd.checkFields();
			phd.buildCheckFieldString();
			requiredAndOptionalAnalysis += phd.getFieldCheckInfo();

			break;
		case "proceedings":

			Proceedings proceedings= new Proceedings(info);
			bibliographiesInfo.add(proceedings);
			proceedings.checkFields();
			proceedings.buildCheckFieldString();
			requiredAndOptionalAnalysis += proceedings.getFieldCheckInfo();

			break;
		case "techreport":

			TechReport techReport= new TechReport(info);
			bibliographiesInfo.add(techReport);
			techReport.checkFields();
			techReport.buildCheckFieldString();
			requiredAndOptionalAnalysis += techReport.getFieldCheckInfo();

			break;
		case "unpublished":

			Unpublished unpublished = new Unpublished(info);
			bibliographiesInfo.add(unpublished);
			unpublished.checkFields();
			unpublished.buildCheckFieldString();
			requiredAndOptionalAnalysis += unpublished.getFieldCheckInfo();

			break;

		default:
			break;
		}
	}

	public void printFormatErrors() {
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("TOTAL DE ERRORES EN EL FORMATO DEL ARCHIVO: "+  erroresFormato);
		System.out.println();
		System.out.println("\t Entradas con ID faltante: " + erroresId);
		System.out.println("\t Errores en cierre o apertura de corchetes en entrada bibliográfica: " + erroresCorchetes);
		System.out.println("\t\t Errores de corchetes en el valor de una llave (key): " + erroresCorchetesEnValor);
		System.out.println("\t Errores por falta de comas separadoras en entrada bibliográfica: " + erroresComas);
		System.out.println("\t Errores de capitalización en la primera letra de una llave (key): " +  erroresCapitalization);
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println();
	}

	public void printBibliographyTypes() {
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("CONTADOR DE TIPOS DE BIBLIOGRAFIAS VALIDAS:");
		System.out.println();
		System.out.println("De las " + contEntries + " entradas bibliográficas detectadas, " + contValidEntries + " son validas.");
		System.out.println();
		System.out.println("Convencion: #validas/#total");
		System.out.println();
		System.out.println("\t Articles: " + articles + "/" + totalArticles);
		System.out.println("\t Books: " + books + "/" + totalBooks);
		System.out.println("\t Booklets: " + booklets + "/" + totalBooklets);
		System.out.println("\t Conferences: " + conferences + "/" + totalConferences);
		System.out.println("\t Inbooks: " + inbooks + "/" + totalInbooks);
		System.out.println("\t Incollections: " + incollections + "/" + totalIncollections);
		System.out.println("\t Inproceedings: " + inproceedings + "/" + totalInproceedings);
		System.out.println("\t Manuals: " + manuals + "/" + totalManuals);
		System.out.println("\t Masters Thesis: " + mastersThesis + "/" + totalMastersThesis);
		System.out.println("\t Miscs: " + miscs + "/" + totalMiscs);
		System.out.println("\t Phd Thesis: " + phdThesis + "/" + totalPhdThesis);
		System.out.println("\t Proceedings: " + proceedings + "/" + totalProceedings);
		System.out.println("\t Tech report: " + techReports + "/" + totalTechReports);
		System.out.println("\t Unpublished: " + unPublished + "/" + totalUnPublished);
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println();
	}

	public void printRequiredAndOptionalAnalysis() {
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println(this.requiredAndOptionalAnalysis);
		System.out.println("---------------------------------------------------------------------------------------------------");
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
	
	private void countTotalBibliographyTypes(String type) {
		switch (type) {

		case "article":
			totalArticles++;
			break;
		case "book":
			totalBooks++;
			break;
		case "booklet":
			totalBooklets++;
			break;
		case "conference":
			totalConferences++;
			break;
		case "inbook":
			totalInbooks++;
			break;
		case "incollection":
			totalIncollections++;
			break;
		case "inproceedings":
			totalInproceedings++;
			break;
		case "manual":
			totalManuals++;
			break;
		case "mastersthesis":
			totalMastersThesis++;
			break;
		case "misc":
			totalMiscs++;
			break;
		case "phdthesis":
			totalPhdThesis++;
			break;
		case "proceedings":
			totalProceedings++;
			break;
		case "techreport":
			totalTechReports++;
			break;
		case "unpublished":
			totalUnPublished++;
			break;

		default:
			break;
		}
	}


}
