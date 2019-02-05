package model.world;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Bibliography {

	public static final String AUTHOR = "author";
	public static final String TITLE = "title";
	public static final String JOURNAL = "journal";
	public static final String YEAR = "year";
	public static final String VOLUME = "volume";
	public static final String NUMBER = "number";
	public static final String PAGES = "pages";
	public static final String MONTH = "month";
	public static final String DOI = "doi";
	public static final String NOTE = "note";
	public static final String KEY = "key";
	public static final String PUBLISHER = "publisher";
	public static final String SERIES = "series";
	public static final String ADDRESS = "address";
	public static final String EDITION = "edition";
	public static final String URL = "url";
	public static final String HOWPUBLISHED = "howpublished";
	public static final String CHAPTER = "chapter";
	public static final String TYPE = "type";
	public static final String BOOKTITLE = "booktitle";
	public static final String ORGANIZATION = "organization";
	public static final String SCHOOL = "school";
	public static final String INSTITUTION = "institution";

	protected String[] required;
	protected String[] optional;
	protected String fieldCheckInfo;
	protected int contRequired;
	protected int contOptional;
	protected List<String> camposRequeridosNoUtilizados;
	protected List<String> camposOpcionalesUtilizados;

	protected String info;

	public Bibliography(String info) {
		this.info = info;
		this.contOptional = 0;
		this.contRequired = 0;
		this.fieldCheckInfo = "";
		this.camposRequeridosNoUtilizados = new ArrayList<>();
		this.camposOpcionalesUtilizados = new ArrayList<>();
	}

	public String[] getRequired() {
		return required;
	}

	public String[] getOptional() {
		return optional;
	}

	public String getFieldCheckInfo() {
		return fieldCheckInfo;
	}

	public int getContRequired() {
		return contRequired;
	}

	public int getContOptional() {
		return contOptional;
	}	

	public void checkFields() {
		String[] lineas = info.split("\t");

		for(int i=0;i<lineas.length;i++) {
			String typeToCheck = lineas[i].split("=")[0].trim().toLowerCase();
			if(typeToCheck.isEmpty()) {
				continue;
			}
			for(int j = 0;j<required.length;j++) {
				if(typeToCheck.equals(required[j])){
					contRequired++;
					required[j] = null;
				}
			}
			for(int k = 0;k<optional.length;k++) {
				if(typeToCheck.equals(optional[k])){
					contOptional++;
					camposOpcionalesUtilizados.add(optional[k]);
				}
			}
		}
		for(int i=0;i<required.length;i++) {
			if(required[i] != null) {
				camposRequeridosNoUtilizados.add(required[i]);
			}
		}
	}

	public boolean checkKeyValue(String key, String value) {
		boolean valid = true;

		switch (key) {
		case AUTHOR:
			/*
			Pattern pattern = Pattern.compile("[a-zA-Z]")
			if()*/
			break;
		case TITLE:

			break;
		case JOURNAL:

			break;
		case YEAR:

			break;
		case VOLUME:

			break;
		case NUMBER:

			break;
		case PAGES:

			break;
		case MONTH:

			break;
		case DOI:

			break;
		case NOTE:

			break;
		case KEY:

			break;
		case PUBLISHER:

			break;
		case SERIES:

			break;
		case ADDRESS:

			break;
		case EDITION:

			break;
		case URL:

			break;
		case HOWPUBLISHED:

			break;
		case CHAPTER:

			break;
		case TYPE:

			break;
		case BOOKTITLE:

			break;
		case ORGANIZATION:

			break;
		case INSTITUTION:

			break;
		case SCHOOL:

			break;
		default:
			
			break;
		}
		
		
		return valid;
	}

}
