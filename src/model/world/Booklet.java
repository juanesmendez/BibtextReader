package model.world;

public class Booklet extends Bibliography {
	/*
	//Required fields
	private String id;
	private String title;
	//Optional fields
	private String author;
	private String howPublished;
	private String address;
	private String month;
	private String year;
	private String note;
	private String key;
	*/
	private String[] required;
	private String[] optional;
	
	public Booklet(String info) {
		super(info);
		fillRequiredArray();
		fillOptionalArray();
	}
	
	public void fillRequiredArray() {
		required = new String[1];
		required[0] = "title";
	}

	public void fillOptionalArray() {
		optional = new String[7];
		optional[0] = "author";
		optional[1] = "howpublished";
		optional[2] = "address";
		optional[3] = "month";
		optional[4] = "year";
		optional[5] = "note";
		optional[6] = "key";
	}
	
	public void buildCheckFieldString() {

		super.fieldCheckInfo += "\t Booklet:\n"
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
