package com.retocache.models;

public class ParametrosModel {
	private String aplicacion;
	private String parametro;
	private String valorCorto;
	private String valorLargo;

	public String getAplicacion() { return aplicacion; }

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String getValorCorto() {
		return valorCorto;
	}

	public void setValorCorto(String valorCorto) {
		this.valorCorto = valorCorto;
	}

	public String getValorLargo() {
		return valorLargo;
	}

	public void setValorLargo(String valorLargo) {
		this.valorLargo = valorLargo;
	}

	@Override
	public String toString() {
		return "ParametrosModel{" +
				"aplicacion='" + aplicacion + '\'' +
				", parametro='" + parametro + '\'' +
				", valorCorto=" + valorCorto +
				", valorLargo=" + valorLargo +
				'}';
	}
}
