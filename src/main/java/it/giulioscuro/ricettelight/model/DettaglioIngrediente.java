package it.giulioscuro.ricettelight.model;

public class DettaglioIngrediente {
	

	long id;
	

	int calorie;
	

	int grassi;
	

	int carboidrati;
	

	int proeteine;

	private Ingrediente ingrediente;

	//costruttore
	
	public Ingrediente getIngrediente() {
		return ingrediente;
	}
	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}
	public DettaglioIngrediente() {
		
	}
	public DettaglioIngrediente(int calorie, int grassi, int carboidrati, int proeteine) {
		this.calorie = calorie;
		this.grassi = grassi;
		this.carboidrati = carboidrati;
		this.proeteine = proeteine;
	}
	
	//getters e setters
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getCalorie() {
		return calorie;
	}
	public void setCalorie(int calorie) {
		this.calorie = calorie;
	}
	public int getGrassi() {
		return grassi;
	}
	public void setGrassi(int grassi) {
		this.grassi = grassi;
	}
	public int getCarboidrati() {
		return carboidrati;
	}
	public void setCarboidrati(int carboidrati) {
		this.carboidrati = carboidrati;
	}
	public int getProeteine() {
		return proeteine;
	}
	public void setProeteine(int proeteine) {
		this.proeteine = proeteine;
	}


}
