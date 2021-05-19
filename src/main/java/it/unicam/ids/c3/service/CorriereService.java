package it.unicam.ids.c3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.unicam.ids.c3.Repository.FornitoreRepository;
import it.unicam.ids.c3.Repository.LockerRepository;
import it.unicam.ids.c3.entity.Negozio;
import it.unicam.ids.c3.entity.ProdottoIngrosso;
import it.unicam.ids.c3.Repository.IndirizziNegoziRepository;
import it.unicam.ids.c3.Repository.C3Repository;
import it.unicam.ids.c3.entity.ProdottoNegozio;

@Service
@Component
public class CorriereService {
	@Autowired
	IndirizziNegoziRepository negozio;
	@Autowired
	C3Repository prodottiNegozio;
	@Autowired
	LockerRepository locker;
	@Autowired
	CommessoService commesso;
	@Autowired
	CommercianteService commerciante;
	
	List<ProdottoIngrosso> listaConsegna = new ArrayList<ProdottoIngrosso>();
	String nothing = "nome negozio errato";
	
	
	
	public int gotList(List<ProdottoIngrosso> prodottiListaConsegna) {
		for(ProdottoIngrosso p : prodottiListaConsegna) {
			listaConsegna.add(p);
		}
		return 0;
	}
	
	
	public String getOneAddres(String nomeNegozio) {
		List<Negozio> neg = negozio.findAll(); //mi scarico tutto il db dentro una lista d'appoggio 

		for(int i = 0; i< neg.size(); i++) { //scorro tutti gli oggetti nel database per trovare la corrispondenza con quello passato dal corriere
			if(neg.get(i).getNome().equals(nomeNegozio)) { //controllo se ci sono corrispondenze
				return neg.get(i).getIndirizzo(); //ritorno l'indirizzo richiesto dal corriere
			}
			
		}
		return nothing; // se non trovo niente ritorno la stringa "indirizzo non trovato"
	}


	public List<ProdottoNegozio> prelevaProdotti(List<ProdottoNegozio> prodottiListaOrdine) {
		
		return commesso.prelevaProdotti(prodottiListaOrdine); //richiamo il metodo preleva prodotti sul negozio cosi da avere i prodotti ritirati  pronto per la notifica sul sistema
		
	}


	public String getDestinazioneNegozio(String nome) {
		return commesso.destinazioneTrasposrtoNegozio(nome);
	}
	
	public String getDestinazioneLocker(String nome) {
		return commesso.destinazioneTrasposrtoLocker(nome);
	}
	
	public String getDestinazioneDomicilio(String nome) {
		return commesso.destinazioneTrasposrtoDomicilio(nome);
	}


	public String rilasciaMerceLocker(List<ProdottoNegozio> prodotti) {
		
		if(prodotti.removeAll(prodotti)) { //i prodotti una volta scaricati non ci sono più nella lista, quindi li elimino e se ritorna true
			return "rilascio al locker avvenuto";
		}else { //se ritorna false
			return "rilascio non avvenuto";
		}
		
	}

	public String rilasciaMerceNegozio(List<ProdottoIngrosso> prodotti) {
		commerciante.updateDatabase(prodotti);
		if(prodotti.removeAll(prodotti)) { //i prodotti una volta scaricati non ci sono più nella lista, quindi li elimino e se ritorna true
			return "rilascio al Negozio avvenuto";
		}else { //se ritorna false
			return "rilascio non avvenuto";
		}
			
	}
		
	public String rilasciaMerceDomicilio(List<ProdottoNegozio> prodotti) {
			
		if(prodotti.removeAll(prodotti)) { //i prodotti una volta scaricati non ci sono più nella lista, quindi li elimino e se ritorna true
			return "rilascio al Negozio avvenuto";
		}else { //se ritorna false
			return "rilascio non avvenuto";
		}
			
	}


}
