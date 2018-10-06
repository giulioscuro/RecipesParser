package it.giulioscuro.ricettelight.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="FONTE")
public class Fonte {
	
	@Transient
	public static final int MAX_ELEMENTS_IN_PAGE = 15;
	
	@Id
	private String sigla;
	
	@Column(name="SITE_URL")
	private String url;
	
	
	@Column(name="SITE_ICON")
	private String icon;
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Transient
	private String listUrl;
	
	@Transient
	private int pageToScan;
	
	
	
	@OneToMany(mappedBy="fonte")
	List<Ricetta> ricette;
	
	public List<Ricetta> getRicette() {
		return ricette;
	}

	public void setRicette(List<Ricetta> ricette) {
		this.ricette = ricette;
	}

	public Fonte() {
		System.out.println("Creato oggetto di tipo Fonte");
	}
	
	public int getPageToScan() {
		return pageToScan;
	}
	public void setPageToScan(int pageToScan) {
		this.pageToScan = pageToScan;
	}
	public String getListUrl() {
		return listUrl;
	}
	public void setListUrl(String listUrl) {
		this.listUrl = listUrl;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void addRicetta(Ricetta r) {
		if(ricette ==null) {
			ricette = new ArrayList<Ricetta>();
		}
		
		r.setFonte(this);
		ricette.add(r);
	
	}

	@Override
	public String toString() {
		return "Fonte [sigla=" + sigla + ", url=" + url + ", listUrl=" + listUrl + ", pageToScan=" + pageToScan
				+ ", ricette=" + ricette + "]";
	}

}
