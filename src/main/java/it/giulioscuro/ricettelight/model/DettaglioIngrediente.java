package it.giulioscuro.ricettelight.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name="DETTAGLI_INGREDIENTE")
public class DettaglioIngrediente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "DETTAGLIO_SEQ")
	long id;
	
	@Column(name = "calorie")
	int calorie;
	
	@Column(name = "grassi")
	int grassi;
	
	@Column(name = "carboidrati")
	int carboidrati;
	
	@Column(name = "proteine")
	int proeteine;
	
	@OneToOne(mappedBy="dettaglioIngrediente" ,cascade=CascadeType.ALL)
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
