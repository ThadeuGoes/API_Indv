package br.com.api.trabalhoIndividual.enums;

public enum TipoRoleEnum {

	ROLE_COMPRADOR("COMPRADOR"), ROLE_VENDEDOR("VENDEDOR");

	private String tipo;

	TipoRoleEnum(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
}