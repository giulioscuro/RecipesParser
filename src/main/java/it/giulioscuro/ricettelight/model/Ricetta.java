package it.giulioscuro.ricettelight.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "TABLE_RICETTE")
public class Ricetta implements Serializable{
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -572036528811619118L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "RICETTE_SEQ")
	private long id;
	
	@Column(name="ID_FONTE")
	private long id_fonte;

	public long getId_fonte() {
		return id_fonte;
	}

	public void setId_fonte(long id_fonte) {
		this.id_fonte = id_fonte;
	}

	@Column
	private String titolo;
	

	@Column(name="IMAGE_URL")
	private String imageUrl;
	

	@Column(name="LINK_URL")
	private String linkUrl;
	

	@Column
	private String descrizione;
	

	@Column
	private String istruzioni;
	
	@Transient
	List<Ingrediente> ingredienti;
	
	@ManyToOne
	@JoinColumn(name="SIGLA_FONTE")
	private Fonte fonte;

	


	public Fonte getFonte() {
		return fonte;
	}

	public void setFonte(Fonte fonte) {
		this.fonte = fonte;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getIstruzioni() {
		return istruzioni;
	}

	public void setIstruzioni(String istruzioni) {
		this.istruzioni = istruzioni;
	}

	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}



	@Override
	public String toString() {
		return "Ricetta [id=" + id + ", id_fonte=" + id_fonte + ", titolo=" + titolo + ", imageUrl=" + imageUrl
				+ ", linkUrl=" + linkUrl + ", descrizione=" + descrizione + ", istruzioni=" + istruzioni
				+ ", ingredienti=" + ingredienti + ", fonte=" + fonte + "]";
	}
	
	
}
