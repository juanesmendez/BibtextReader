package model.world;

public class Misc extends Bibliography{
	/*
	private String id;
	//Optional fields
	
	private String author;
	private String title;
	private String howPublished;
	private String month;
	private int year;
	private String note;
	private String key;
	*/
	
	private String[] required;
	private String[] optional;

	public Misc(String info) {
		super(info);
		fillRequiredArray();
		fillOptionalArray();
	}

	public void fillRequiredArray() {
		required = new String[0];
	}

	public void fillOptionalArray() {
		optional = new String[7];
		optional[0] = "author";
		optional[1] = "title";
		optional[2] = "howpublished";
		optional[3] = "month";
		optional[4] = "year";
		optional[5] = "note";
		optional[6] = "key";
	}
	
	public void buildCheckFieldString() {

		super.fieldCheckInfo += "\t Misc:\n"
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
