package it.unicam.ids.c3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.unicam.ids.c3.entity.Indirizzo;
import it.unicam.ids.c3.entity.Locker;
import it.unicam.ids.c3.entity.Negozio;
import it.unicam.ids.c3.Repository.IndirizziNegoziRepository;
import it.unicam.ids.c3.Repository.IndirizziRepository;
import it.unicam.ids.c3.Repository.C3Repository;
import it.unicam.ids.c3.Repository.IndirizziLockerRepository;
import it.unicam.ids.c3.entity.ProdottoNegozio;

@Service
@Component
public class CommessoService {
	
	
	@Autowired
	IndirizziNegoziRepository negozio;
	@Autowired
	IndirizziLockerRepository locker;
	@Autowired
	IndirizziRepository domicilio;
	@Autowired
	C3Repository prodotti;
	
	List<ProdottoNegozio> prodottiNegozio = new ArrayList<ProdottoNegozio>();
	
	public List<ProdottoNegozio> getListaMerce() {
		prodottiNegozio = prodotti.findAll();
		return prodottiNegozio;
	}

	public String destinazioneTrasposrtoNegozio(String nome) {
		List<Negozio> ind = new ArrayList<Negozio>();
		ind = negozio.findAll();
		for(int i = 0; i< ind.size(); i++) { //scorro tutti gli oggetti nel database per trovare la corrispondenza con quello passato dal corriere
			if(ind.get(i).getNome().equals(nome)) { //controllo se ci sono corrispondenze
				return ind.get(i).getIndirizzo(); //ritorno l'indirizzo richiesto dal corriere
			}
			
		}
		
		return "indirizzo non trovato";
		 
	}
	
	public String destinazioneTrasposrtoLocker(String nome) {
		List<Locker> ind = new ArrayList<Locker>();
		ind = locker.findAll();
		for(int i = 0; i< ind.size(); i++) { //scorro tutti gli oggetti nel database per trovare la corrispondenza con quello passato dal corriere
			if(ind.get(i).getNome().equals(nome)) { //controllo se ci sono corrispondenze
				return ind.get(i).getIndirizzo(); //ritorno l'indirizzo richiesto dal corriere
			}
			
		}
		
		return "Nome Locker errato!";
		 
	}
	
	public String destinazioneTrasposrtoDomicilio(String nome) {
		List<Indirizzo> ind = new ArrayList<Indirizzo>();
		ind = domicilio.findAll();
		for(int i = 0; i< ind.size(); i++) { //scorro tutti gli oggetti nel database per trovare la corrispondenza con quello passato dal corriere
			if(ind.get(i).getNome().equals(nome)) { //controllo se ci sono corrispondenze
				return ind.get(i).getIndirizzo(); //ritorno l'indirizzo richiesto dal corriere
			}
			
		}
		
		return "indirizzo non trovato";
		 
	}
	


	public double getPrezzo(List<ProdottoNegozio> listaCarrello) {
		double tot = 0;
		for(int i = 0; i<listaCarrello.size(); i++) {
			if(listaCarrello.get(i).getSconto()!= 0.0) {
				double prezzoInSconto = (listaCarrello.get(i).getPrezzo() * listaCarrello.get(i).getSconto()) /100; // calcolo lo soconto
				tot += prezzoInSconto; // aggiorno il totale
			}else {
				tot += listaCarrello.get(i).getPrezzo(); //aggiorno il totale se lo sconto è uguale a 0.0
			}
		}
		return tot; // ritorno il totale
	}
	

	
	public List<ProdottoNegozio> prelevaProdotti(List<ProdottoNegozio> listaProdottiRitiro) {
		
		List<ProdottoNegozio> lista = new ArrayList<ProdottoNegozio>(); //creo lista d'appoggio per scaricare tutti prodotti dal db
		lista = prodotti.findAll(); //scarico tutti i prodotti del db nella lista d'appoggio
		
		List<ProdottoNegozio> listaConsegna = new ArrayList<ProdottoNegozio>(); //creo una lista nuova per metterci tutti i prodotti da dare al corriere
		
		
		for(int i = 0; i<listaProdottiRitiro.size(); i++) { //scorro la lista degli oggetti da ritirare passata dal corriere
			
			for(int k = 0; k<lista.size(); k++) { //scorro tutti i prodotti presenti nel database
				
				if(listaProdottiRitiro.get(i).getNome().equals(lista.get(k).getNome())) { //confronto se i due oggetti sono uguali
						listaConsegna.add(lista.get(k)); //aggiungo l'oggetto alla lista dei prodotti da dare al corriere
						lista.get(i).setQuantita( lista.get(i).getQuantita() - listaProdottiRitiro.get(k).getQuantita());
						prodotti.save(lista.get(i));
						
				}
				
				
			}
			
		}
		return listaConsegna;
	}

	
	public String venditaProdotti(ArrayList<ProdottoNegozio> listaCarrello) {
		List<ProdottoNegozio> lista = prodotti.findAll();
		for(ProdottoNegozio p : listaCarrello){
			for(ProdottoNegozio c : lista) {
				if(p.getNome().equals(c.getNome())) {
					c.setQuantita(c.getQuantita()-p.getQuantita());					
				}
			}
		}
		
		prodotti.saveAll(lista);
		
		return "i prodotti sono stati aggiornati, verranno  inviati subito in cassa per procedere con il pagamento!";
	}

	public List<ProdottoNegozio> ritiroLista(List<ProdottoNegozio> lista) {
		List<ProdottoNegozio> neg = prodotti.findAll();
		List<ProdottoNegozio> ret = new ArrayList<ProdottoNegozio>();
		
		for(ProdottoNegozio p : lista) {
			for(ProdottoNegozio l : neg) {
				if(p.getNome().equals(l.getNome()) && p.getQuantita()<= l.getQuantita()) {
					ret.add(p); //siccome se setto la quantità prima dopo ritorno una quantità sbagliata allora se entro dentro all'if vuol dire che il prodotto 
					//cel ho disponibile e allora setto la quantità e ritornoil prodotto passato dalla lista che ha la quantità richiesta dal cliente
				}	
			}
		}
		
		return ret;
	}
	

	public List<Locker> ComunicaPuntoPrelievo(String nomeCitta) {
		
		List<Locker> lockers = locker.findAll();
		List<Locker> AllLocker = new ArrayList<Locker>();
		
		for(Locker c : lockers) {
			if(c.getCitta().equals(nomeCitta)) {
				AllLocker.add(c);
			}
		}
		return AllLocker;
	}

	public String ComunicaDomicilio(String nomeCitta, String indirizzo) {
		List<Indirizzo> ind = domicilio.findAll();
		for(Indirizzo i : ind) {
			if(i.getCitta().equals(nomeCitta) && i.getIndirizzo().equals(indirizzo)) {
				return "Il suo indirizzo si trova nel centro storico, la merce gli verrà consegnagta a casa";
			}
		}
		return "La sua abitazione si trova fuori dal centro storico, per favore controlli il locker piu vicino a lei";
	}

}
