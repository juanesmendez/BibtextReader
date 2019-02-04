package model.world;

public class Inbook extends Bibliography{
	//Required fields
	/*
	private String id;
	
	private String author; private String editor;
	private String title;
	private int chapter; private String pages;
	private String publisher;
	private int year;
	
	//Optional fields
	
	private int volume; private int number;
	private String series;
	private String type;
	private String address;
	private int edition;
	private int month;
	private String note;
	private String key;*/
	
	private String[] required;
	private String[] optional;
	
	public Inbook(String info) {
		super(info);
		fillRequiredArray();
		fillOptionalArray();
	}
	
	public void fillRequiredArray() {
		required = new String[5];
		required[0] = "author/editor";
		required[1] = "title";
		required[2] = "chapter/pages";
		required[3] = "publisher";
		required[4] = "year";
	}

	public void fillOptionalArray() {
		optional = new String[8];
		optional[0] = "volume/number";
		optional[1] = "series";
		optional[2] = "type";
		optional[3] = "address";
		optional[4] = "edition";
		optional[5] = "month";
		optional[6] = "note";
		optional[7] = "key";
	}
	
	public void buildCheckFieldString() {

		super.fieldCheckInfo += "\t Inbook:\n"
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
