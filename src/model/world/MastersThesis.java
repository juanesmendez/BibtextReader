package model.world;

public class MastersThesis extends Thesis{
	public MastersThesis(String info) {
		super(info);
	}
	
	public void buildCheckFieldString() {

		super.fieldCheckInfo += "\t Masters Thesis:\n"
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
