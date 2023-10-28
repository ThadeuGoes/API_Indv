package br.com.api.trabalhoIndividual.enums;

public enum TipoRoleEnum {

	ROLE_LOCATARIO("LOCATARIO"), 
	ROLE_FUNCIONARIO("FUNCIONARIO");

	private String tipo;

	TipoRoleEnum(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
}