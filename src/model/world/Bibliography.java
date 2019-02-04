package model.world;

import java.util.ArrayList;
import java.util.List;

public class Bibliography {
	
	protected String[] required;
	protected String[] optional;
	protected String fieldCheckInfo;
	protected int contRequired;
	protected int contOptional;
	protected List<String> camposRequeridosNoUtilizados;
	protected List<String> camposOpcionalesUtilizados;
	
	protected String info;
	
	public Bibliography(String info) {
		this.info = info;
		this.contOptional = 0;
		this.contRequired = 0;
		this.fieldCheckInfo = "";
		this.camposRequeridosNoUtilizados = new ArrayList<>();
		this.camposOpcionalesUtilizados = new ArrayList<>();
	}

	public String[] getRequired() {
		return required;
	}

	public String[] getOptional() {
		return optional;
	}

	public String getFieldCheckInfo() {
		return fieldCheckInfo;
	}

	public int getContRequired() {
		return contRequired;
	}

	public int getContOptional() {
		return contOptional;
	}	
	
	public void checkFields(String type) {
		//System.out.println("INFOOOOOO: "+info);
		String[] lineas = info.split("\t");
		
		for(int i=0;i<lineas.length;i++) {
			String typeToCheck = lineas[i].split("=")[0].trim().toLowerCase();
			if(typeToCheck.isEmpty()) {
				continue;
			}
			for(int j = 0;j<required.length;j++) {
				if(typeToCheck.equals(required[j])){
					contRequired++;
					required[j] = null;
				}
			}
			for(int k = 0;k<optional.length;k++) {
				if(typeToCheck.equals(optional[k])){
					contOptional++;
					camposOpcionalesUtilizados.add(optional[k]);
				}
			}
		}

		for(int i=0;i<required.length;i++) {
			if(required[i] != null) {
				camposRequeridosNoUtilizados.add(required[i]);
			}
		}

		//buildCheckFieldString(camposRequeridosNoUtilizados,camposOpcionalesUtilizados);
		
	}
	
}
