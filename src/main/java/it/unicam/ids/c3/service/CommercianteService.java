package it.unicam.ids.c3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.unicam.ids.c3.Repository.C3Repository;
import it.unicam.ids.c3.Repository.IndirizziNegoziRepository;
import it.unicam.ids.c3.entity.Negozio;
import it.unicam.ids.c3.entity.ProdottoIngrosso;
import it.unicam.ids.c3.entity.ProdottoNegozio;

@Service
@Component
public class CommercianteService {

//	private List<ProdottoIngrosso> prodottiListaOrdine = new ArrayList<ProdottoIngrosso>();
	double portafoglio = 0;
	
	@Autowired
	FornitoreService fornitore;
	@Autowired
	C3Repository c3Repository;
	@Autowired 
	IndirizziNegoziRepository negozi;
	
	public CommercianteService() {
		
	}
	
	public CommercianteService(/*List<ProdottoIngrosso> prodottiListaOrdine,*/ double portafoglio) {
		super();
//		this.prodottiListaOrdine = prodottiListaOrdine;
		this.portafoglio = portafoglio;
	} 
	
	
	public List<ProdottoIngrosso> inviaLista(List<ProdottoIngrosso> prodottiListaOrdine){
		
		prodottiListaOrdine = fornitore.disponibilitaMerce(prodottiListaOrdine);
		double c = fornitore.pagamento(this.portafoglio);
		setPortafoglio(c);
		//trasporto merce negozio
		return prodottiListaOrdine;
		
	}
	
	
	public List<ProdottoNegozio> lancioPromozioni(String categoria, double sconto){
		List<ProdottoNegozio> prodottiNegozio = c3Repository.findAll();
		List<ProdottoNegozio> prodottiScontati = new ArrayList<ProdottoNegozio>();
		
		for(ProdottoNegozio p : prodottiNegozio) {
			if(p.getCategoria().equals(categoria)) {
				p.setSconto(sconto);
				prodottiScontati.add(p);
			}
		}
		
		c3Repository.saveAll(prodottiNegozio);
		
		
		return prodottiScontati;
	}
	
	public void updateDatabase(List<ProdottoIngrosso> prodottiListaOrdine) {
		
		List<ProdottoNegozio> a = new ArrayList<ProdottoNegozio>();
		for(ProdottoIngrosso c : prodottiListaOrdine  ) {
			ProdottoNegozio tmp = new ProdottoNegozio();
			tmp.setCategoria(c.getCategoria());
			tmp.setSconto(0.0);
			tmp.setNome(c.getNome());
			double aggiunta = (c.getPrezzo()*20)/100;
			tmp.setPrezzo(c.getPrezzo() + aggiunta);
			tmp.setQuantita(c.getQuantita());
			a.add(tmp);
		}
		
		c3Repository.saveAll(a);
	}
	


	public int richiediStatistiche(String nomeNegozio) {
		List<Negozio> neg = negozi.findAll();
		
		for(Negozio n : neg){
			if(n.getNome().equals(nomeNegozio)) {
				return n.getPresenza();
			}
		}
		
		return 0;
	}
	
	

	public double getPortafoglio() {
		return portafoglio;
	}


	public void setPortafoglio(double portafoglio) {
		this.portafoglio = portafoglio;
	}

	public String cancellaPromozioni(String categoria) {
		List<ProdottoNegozio> prod = c3Repository.findAll();
		
		for(ProdottoNegozio p : prod) {
			if(p.getCategoria().equals(categoria)) {
				p.setSconto(0.0);
			}
			c3Repository.save(p);
		}
		return "Categoria: " + categoria +" aggiornata";
	}
	
	
}
