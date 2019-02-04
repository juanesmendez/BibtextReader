package model.world;

import java.util.ArrayList;
import java.util.List;

public class Article extends Bibliography{

	/*
	//Required fields
	private String id;
	private String author;
	private String title;
	private String journal;
	private int year;
	private int volume;
	//Optional fields
	private String number;
	private String pages;
	private String month;
	private String doi;
	private String note;
	private String key;
	 */

	public Article(String info) {
		super(info);
		fillRequiredArray();
		fillOptionalArray();
	}

	public void fillRequiredArray() {
		required = new String[5];
		required[0] = "author";
		required[1] = "title";
		required[2] = "journal";
		required[3] = "year";
		required[4] = "volume";

	}

	public void fillOptionalArray() {
		optional = new String[6];
		optional[0] = "number";
		optional[1] = "pages";
		optional[2] = "month";
		optional[3] = "doi";
		optional[4] = "note";
		optional[5] = "key";
	}

	

	public void buildCheckFieldString() {

		super.fieldCheckInfo += "\t Article:\n"
				+ "\t\t Campos requeridos faltantes: " + camposRequeridosNoUtilizados.size() +"\n";
		for(String st:camposRequeridosNoUtilizados) {
			super.fieldCheckInfo += "\t\t\t\t " + st + "\n";
		}
		super.fieldCheckInfo += "\n\t\t Campos opcionales utilizados: " + contOptional + "\n";
		for(String st:camposOpcionalesUtilizados) {
			super.fieldCheckInfo += "\t\t\t\t " + st + "\n";
		}	
	}


}