package model.world;

public class Thesis extends Bibliography{
	//Required fields
	/*
	private String id;
	private String author;
	private String title;
	private String school;
	private int year;

	//Optional fields

	private String type;
	private String address;
	private int month;
	private String note;
	*/

	public Thesis(String info) {
		super(info);
		fillRequiredArray();
		fillOptionalArray();
	}

	public void fillRequiredArray() {
		required = new String[4];
		required[0] = "author";
		required[1] = "title";
		required[2] = "school";
		required[3] = "year";
	}

	public void fillOptionalArray() {
		optional = new String[4];
		optional[0] = "type";
		optional[1] = "address";
		optional[2] = "month";
		optional[3] = "note";
	}
	
	public void buildCheckFieldString() {

		super.fieldCheckInfo += "\t Thesis:\n"
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
