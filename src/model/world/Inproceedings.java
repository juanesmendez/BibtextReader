package model.world;

public class Inproceedings extends Bibliography{
	/*
	private String id;

	private String author; 
	private String title;
	private String bookTitle;
	private int year;

	//Optional fields
	private String editor;
	private int volume; private int number;
	private String series;
	private String pages;
	private String address;
	private String month;
	private String organization;
	private String publisher;
	private String note;
	private String key;
	*/

	public Inproceedings(String info) {
		super(info);
		fillRequiredArray();
		fillOptionalArray();
	}

	public void fillRequiredArray() {
		required = new String[4];
		required[0] = "author";
		required[1] = "title";
		required[2] = "booktitle";
		required[3] = "year";
	}

	public void fillOptionalArray() {
		optional = new String[10];
		optional[0] = "editor";
		optional[1] = "volume/number";
		optional[2] = "series";
		optional[3] = "pages";
		optional[4] = "address";
		optional[5] = "month";
		optional[6] = "organization";
		optional[7] = "publisher";
		optional[8] = "note";
		optional[9] = "key";
	}
	
	public void buildCheckFieldString() {

		super.fieldCheckInfo += "\t Inproceedings:\n"
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
