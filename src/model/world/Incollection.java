package model.world;

public class Incollection extends Bibliography{
	//Required fields
	/*
	private String id;
	
	private String author;
	private String title;
	private String bookTitle;
	private String publisher;
	private String year;
	//Optional fields
	private String editor;
	private int volume;private int number;
	private String series;
	private String type;
	private int chapter;
	private String pages;
	private String address;
	private int edition;
	private String month;
	private String note;
	private String key;
	*/
	
	public Incollection(String info) {
		super(info);
		fillRequiredArray();
		fillOptionalArray();
	}
	
	public void fillRequiredArray() {
		required = new String[5];
		required[0] = "author";
		required[1] = "title";
		required[2] = "booktitle";
		required[3] = "publisher";
		required[4] = "year";
	}

	public void fillOptionalArray() {
		optional = new String[11];
		optional[0] = "editor";
		optional[1] = "volume/number";
		optional[2] = "series";
		optional[3] = "type";
		optional[4] = "chapter";
		optional[5] = "pages";
		optional[6] = "address";
		optional[7] = "edition";
		optional[8] = "month";
		optional[9] = "note";
		optional[10] = "key";
	}
	
	public void buildCheckFieldString() {

		super.fieldCheckInfo += "\t Incollection:\n"
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
