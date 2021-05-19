package it.unicam.ids.c3.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unicam.ids.c3.Repository.FornitoreRepository;
import it.unicam.ids.c3.entity.ProdottoIngrosso;
import it.unicam.ids.c3.entity.ProdottoNegozio;

@Service
public class FornitoreService {
	
	private List<ProdottoIngrosso> prodottiDisponibili = new ArrayList<ProdottoIngrosso>();
	
	List<ProdottoIngrosso> ordine = new ArrayList<ProdottoIngrosso>();

	@Autowired
	FornitoreRepository repository;
	@Autowired
	CorriereService corriere;
	
	double fattura;
	
	public  List<ProdottoIngrosso> disponibilitaMerce(List<ProdottoIngrosso> prodottiListaOrdine) {
		setFattura(0.0);
		double costoTot = 0;
		prodottiDisponibili = repository.findAll();
		for(ProdottoIngrosso prodotto : prodottiListaOrdine) {
			
			for(int i = 0 ; i < prodottiDisponibili.size(); i++) {

				
				if((prodottiDisponibili.get(i).equals(prodotto)) && (prodotto.getQuantita() <= prodottiDisponibili.get(i).getQuantita())) {
					
					ordine.add(prodotto); //aggiungo il prodotto alla lista dell'ordine
					int qta =  prodotto.getQuantita() - prodottiDisponibili.get(i).getQuantita();
					prodottiDisponibili.get(i).setQuantita(qta);//aggiorno la quantita del prodotto dopo che è stato aggiunto alla lista
					repository.save(prodottiDisponibili.get(i)); //salvo il prodotto aggiornato con la quantità sul db
					costoTot += prodottiDisponibili.get(i).getPrezzo();

				}
				
			}
		
		}
		setFattura(costoTot);//setto il prezzo finale della fattura in modo da poterlo riprendere dopo per il pagamento
		return ordine;
		
	}


	public double pagamento(double portafoglio) {
		portafoglio = portafoglio - this.fattura;
		return portafoglio;
	}



	public String fornisciMerce(ArrayList<ProdottoIngrosso> prodottiListaConsegna) {
		if(corriere.gotList(prodottiListaConsegna) == 0)
			return "rilascio avvenuto";
		else
			return "rilascio non avvenuto";
	}
	


	public double getFattura() {
		return fattura;
	}



	public void setFattura(double fattura) {
		this.fattura = fattura;
	}

	
}
