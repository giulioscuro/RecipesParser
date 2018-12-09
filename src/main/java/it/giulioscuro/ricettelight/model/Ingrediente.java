package it.giulioscuro.ricettelight.model;

import java.io.Serializable;
import java.util.List;


public class Ingrediente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -600867517956534272L;

	private long id;

	private String nome;

	private String imageUrl;
	

	private String descrizione;


	private String quantita;
	

	private String unitaMisura;
	

	//@Transient
	private DettaglioIngrediente dettaglioIngrediente;
	

	private List<Ricetta> ricette;

	
	public DettaglioIngrediente getDettaglioIngrediente() {
		return dettaglioIngrediente;
	}


	public void setDettaglioIngrediente(DettaglioIngrediente dettaglioIngrediente) {
		this.dettaglioIngrediente = dettaglioIngrediente;
	}



	public Ingrediente() {
		// TODO Auto-generated constructor stub
	}
	


	public String getQuantita() {
		return quantita;
	}


	public void setQuantita(String quantita) {
		this.quantita = quantita;
	}


	public List<Ricetta> getRicette() {
		return ricette;
	}


	public void setRicette(List<Ricetta> ricette) {
		this.ricette = ricette;
	}


	public String getUnitaMisura() {
		return unitaMisura;
	}

	public void setUnitaMisura(String unitaMisura) {
		this.unitaMisura = unitaMisura;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	@Override
	public String toString() {
		return "Ingrediente [id=" + id + ", nome=" + nome + ", imageUrl=" + imageUrl + ", descrizione=" + descrizione
				+ ", quantita=" + quantita + ", unitaMisura=" + unitaMisura + ", dettaglioIngrediente="
				+ dettaglioIngrediente + ", ricette=" + ricette + "]";
	}

	
	

}
