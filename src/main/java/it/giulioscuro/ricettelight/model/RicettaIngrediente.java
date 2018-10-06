package it.giulioscuro.ricettelight.model;



public class RicettaIngrediente {

	private Long idRicetta;

	private Long idIngrediente;

	private String quantitaEsetesa;

	private int quantita;

	private String unitaDiMisura;

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public String getUnitaDiMisura() {
		return unitaDiMisura;
	}

	public void setUnitaDiMisura(String unitaDiMisura) {
		this.unitaDiMisura = unitaDiMisura;
	}

	public long getIdRicetta() {
		return idRicetta;
	}

	public void setIdRicetta(long idRicetta) {
		this.idRicetta = idRicetta;
	}

	public long getIdIngrediente() {
		return idIngrediente;
	}

	public void setIdIngrediente(long idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public String getQuantitaEsetesa() {
		return quantitaEsetesa;
	}

	public void setQuantitaEsetesa(String quantitaEsetesa) {
		this.quantitaEsetesa = quantitaEsetesa;
	}

	@Override
	public String toString() {
		return "RicettaIngrediente [idRicetta=" + idRicetta + ", idIngrediente=" + idIngrediente + ", quantitaEsetesa="
				+ quantitaEsetesa + "]";
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idIngrediente == null) ? 0 : idIngrediente.hashCode());
		result = prime * result
				+ ((idRicetta == null) ? 0 : idRicetta.hashCode());
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {

		System.out.println("chiamata a metodo equals..");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		RicettaIngrediente other = (RicettaIngrediente) obj;

		if (other.getIdIngrediente() == this.idIngrediente && other.getIdRicetta() == other.getIdRicetta()) {
			System.out.println("gli oggetti sono uguali");
			return true;
		}
			

		return false;
	}

}
