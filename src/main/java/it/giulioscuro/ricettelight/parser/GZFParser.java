package it.giulioscuro.ricettelight.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it.giulioscuro.ricettelight.MainClass;
import it.giulioscuro.ricettelight.dao.jdbc.JdbcTemplateRicettaDao;
import it.giulioscuro.ricettelight.model.Fonte;
import it.giulioscuro.ricettelight.model.Ingrediente;
import it.giulioscuro.ricettelight.model.Ricetta;

public class GZFParser implements RicetteParser {

	public Fonte getFonte() {
		return fonte;
	}

	public void setFonte(Fonte fonte) {
		this.fonte = fonte;
	}

	private static final Logger log = LoggerFactory.getLogger(GZFParser.class);
	
	@Autowired
	private JdbcTemplateRicettaDao dao;

	@Autowired
	private Fonte fonte;

	private String tagLista;
	
	boolean scanAllPages = false;

	public Ricetta parseDetail(Element ricettaTAg) throws IOException {
		
		
		String idString = ricettaTAg.attr("data-recipe");
		log.debug("ID RICETTA: " + idString);
		

		Element titoloRicettaTAG = ricettaTAg.select("h2.title-recipe").first();
		Elements link = titoloRicettaTAG.getElementsByTag("a");
		
		
		String linkHref = link.attr("href");
		log.debug("URL RICETTA --> " + linkHref);
		
		String linkText = link.text();
		log.debug("TITOLO RICETTA --> " + linkText);

		Elements descrizioneP = ricettaTAg.getElementsByTag("p");
		String descrizione = descrizioneP.text();
		log.debug("DESCRIZIONE RICETTA --> " + descrizione);

		Elements img = ricettaTAg.getElementsByTag("img");
		String imgSrc = img.attr("src");
		log.debug("IMMAGINE RICETTA -->" + imgSrc);
		// Element content = doc.getElementById("content");
		// Elements links = content.getElementsByTag("a");

		Document docRicetta = Jsoup.connect(linkHref).get();
		
		Element ingredienti = docRicetta.select("div.ingredienti").first();
	
		Elements ingredientQ = ingredienti.select("dd");
		List<Ingrediente> ingredientiList = new ArrayList<Ingrediente>();
		
		//build list of ingredients
		for (Element e : ingredientQ) {

			Element a = e.select("a").first();
			Node node = a.nextSibling();
		
			Ingrediente ingrediente = new Ingrediente();
			ingrediente.setNome(a.text());
			ingrediente.setQuantita(node.toString());
			ingredientiList.add(ingrediente);
			
			log.debug(ingrediente.getNome()+ " " + ingrediente.getQuantita());
		}


		Ricetta r = new Ricetta();
		r.setFonte(fonte);
		r.setId(Integer.valueOf(idString));
		r.setIngredienti(ingredientiList);
		r.setTitolo(linkText);
		r.setImageUrl(imgSrc);
		r.setDescrizione(descrizione);
		r.setLinkUrl(linkHref);
		// ricetteList.add(r);
		return r;
	}

	@Override
	public List<Ricetta> parseList() {

		Document listOfRecipePage;
		List<Ricetta> ricetteList = new ArrayList<Ricetta>();
		
		try {
			String theUrl = fonte.getListUrl();
			log.debug(theUrl);
			for (int i = 1; (i <= fonte.getPageToScan() || scanAllPages) ; i++) {



				String pageUrl = theUrl.concat("/page").concat(String.valueOf(i));
				log.debug(pageUrl);
				
				listOfRecipePage = Jsoup.connect(pageUrl).get();

				Elements ricetteTags = listOfRecipePage.select("article");

				log.debug("Numero di tag ricette in pagina: " + ricetteTags.size());

				//se non trovo più elementi termina ed esce
				if (ricetteTags.size() == 0) {
					return ricetteList;
				}

				for (Element ricettaElement : ricetteTags) {
					String idString = ricettaElement.attr("data-recipe");
					if(!exist(idString)) {
						 Ricetta r = parseDetail(ricettaElement);
						  ricetteList.add(r);
					}
					 
				}
			}

			return ricetteList;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public boolean exist(String idString) {
		// TODO Auto-generated method stub
		return dao.findRicettaByIdFonte(fonte.getSigla(), idString) != null;
	}

}
