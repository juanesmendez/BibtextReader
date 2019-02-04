package model.world;

public class Proceedings extends Bibliography{
	/*
	private String id;
	private String title;
	private int year;
	
	private String editor;
	private int volume; private int number;
	private String series;
	private String address;
	private String month;
	private String publisher;
	private String organization;
	private String note;
	private String key;*/
	
	private String[] required;
	private String[] optional;

	public Proceedings(String info) {
		super(info);
		fillRequiredArray();
		fillOptionalArray();
	}

	public void fillRequiredArray() {
		required = new String[2];
		required[0] = "title";
		required[1] = "year";
		
	}

	public void fillOptionalArray() {
		optional = new String[9];
		optional[0] = "editor";
		optional[1] = "volume/number";
		optional[2] = "series";
		optional[3] = "address";
		optional[4] = "month";
		optional[5] = "publisher";
		optional[6] = "organization";
		optional[7] = "notes";
		optional[8] = "key";
	}
	
	public void buildCheckFieldString() {

		super.fieldCheckInfo += "\t Proceedings:\n"
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
