package it.giulioscuro.ricettelight.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

 @Entity
 @Table(name = "TABLE_INGREDIENTI")
public class Ingrediente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -600867517956534272L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "INGREDIENTI_SEQ")
	@JsonProperty (value = "id")
	private long id;
	

	@Column(name = "nome")
	@JsonProperty (value = "name")
	private String nome;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "descrizione")
	 @JsonIgnore
	private String descrizione;

	@Transient
	 @JsonIgnore
	private String quantita;
	
	@Column(name = "unita_misura")
	 @JsonIgnore
	private String unitaMisura;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="DETTAGLIO_INGREDIENTE_FK")
	//@Transient
	private DettaglioIngrediente dettaglioIngrediente;
	
    @Transient
    @JsonIgnore
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
