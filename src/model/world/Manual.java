package model.world;

public class Manual extends Bibliography{
	/*
	//Required fields
	private String id;

	private String title;

	//Optional fields
	private String author; 
	private String organization;
	private String address;
	private int edition;
	private int month;
	private int year;
	*/



	private String[] required;
	private String[] optional;

	public Manual(String info) {
		super(info);
		fillRequiredArray();
		fillOptionalArray();
	}

	public void fillRequiredArray() {
		required = new String[1];
		required[0] = "title";
	}

	public void fillOptionalArray() {
		optional = new String[6];
		optional[0] = "author";
		optional[1] = "organization";
		optional[2] = "address";
		optional[3] = "edition";
		optional[4] = "month";
		optional[5] = "year";
	}
	
	public void buildCheckFieldString() {

		super.fieldCheckInfo += "\t Manual:\n"
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
