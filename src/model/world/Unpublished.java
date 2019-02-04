package model.world;

public class Unpublished extends Bibliography{
	/*
	private String id;
	
	private String author;
	private String title;
	private String note;
	
	private String month;
	private int year;
	private String key;
	*/

	public Unpublished(String info) {
		super(info);
		fillRequiredArray();
		fillOptionalArray();
	}

	public void fillRequiredArray() {
		required = new String[3];
		required[0] = "author";
		required[1] = "title";
		required[2] = "note";
	}

	public void fillOptionalArray() {
		optional = new String[3];
		optional[0] = "month";
		optional[1] = "year";
		optional[2] = "key";
	}
	
	public void buildCheckFieldString() {

		super.fieldCheckInfo += "\t Unpublished:\n"
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
