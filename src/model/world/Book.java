package model.world;

public class Book extends Bibliography{
	/*
	private String id;
	private String author; private String editor;
	private String title;
	private String publisher;
	private int year;
	
	//Optional fields
	
	private int volume; private int number;
	private String series;
	private String address;
	private int edition;
	private String month;
	private String note;
	private String key;
	private String url;
	*/
	
	public Book(String info) {
		super(info);
		fillRequiredArray();
		fillOptionalArray();
	}
	
	public void fillRequiredArray() {
		required = new String[4];
		required[0] = "author/editor";
		required[1] = "title";
		required[2] = "publisher";
		required[3] = "year";
	}

	public void fillOptionalArray() {
		optional = new String[8];
		optional[0] = "volume/number";
		optional[1] = "series";
		optional[2] = "address";
		optional[3] = "edition";
		optional[4] = "month";
		optional[5] = "note";
		optional[6] = "key";
		optional[7] = "url";
	}
	
	public void buildCheckFieldString() {

		super.fieldCheckInfo += "\t Book:\n"
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
