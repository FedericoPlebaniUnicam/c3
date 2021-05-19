package it.unicam.ids.c3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.unicam.ids.c3.Repository.C3Repository;
import it.unicam.ids.c3.Repository.IndirizziNegoziRepository;
import it.unicam.ids.c3.entity.Negozio;
import it.unicam.ids.c3.entity.ProdottoNegozio;

@Service
@Component
public class ClienteService {
	double portafoglio = 0;
	
	@Autowired
	CommessoService commesso;
	@Autowired
	C3Repository c3repository;
	@Autowired
	IndirizziNegoziRepository negozi;
	@Autowired
	GestoreLockerService locker;
	
	public ClienteService() {
		
	}

	public ClienteService(double portafoglio) {
		super();
		this.portafoglio = portafoglio;
	}
	
	public String getTotale(List<ProdottoNegozio> listaCarrello) {
		double totale = commesso.getPrezzo( listaCarrello);
		return pagaTotale(totale);
		
	}

	private String pagaTotale(double totale) {
		double pago= this.getPortafoglio()-totale; //calcolo la somma da pagare
		this.setPortafoglio(pago); //setto quanto pagato
		return "Pagamento effettuato. Arrivederci"; //pagamento effettuato con successo
	}

	

	public List<ProdottoNegozio> filtroPromozioni(String categoria) {
		
		List<ProdottoNegozio> prodottiNegozio = c3repository.findAll();
		List<ProdottoNegozio> prodottiInPromozione = new ArrayList<ProdottoNegozio>();
		
		for(ProdottoNegozio p :prodottiNegozio) {
			if(p.getCategoria().equals(categoria) && p.getSconto()!=0.0) {
				prodottiInPromozione.add(p); 
			}
		}
		return prodottiInPromozione;
	}
	
	

	public List<Negozio> ricercaPuntiVendita(String categoria) {
		List<Negozio> neg = negozi.findAll();
		List<Negozio> negozi = new ArrayList<Negozio>();
		
		for(Negozio n : neg) {
			if(n.getCategoria().equals(categoria)) {
				negozi.add(n);
			}
		}
		return negozi;
	}
	
	
	
	public double getPortafoglio() {
		return portafoglio;
	}

	public void setPortafoglio(double portafoglio) {
		this.portafoglio = portafoglio;
	}

	public String rilevaPresenza(String nomeNegozio) {
		List<Negozio> neg=negozi.findAll();
		int flag = 0;
		for(Negozio n : neg) {
			if(n.getNome().equals(nomeNegozio)) {
				int x = n.getPresenza();
				n.setPresenza(x+1);
				flag = 1;
			}
			negozi.save(n);
		}
		if(flag == 1)
			return "presenza rilevata";
		else
			return "nome del negozio errato";
	}
	
	public String rilevaUscita(String nomeNegozio) {
		List<Negozio> neg=negozi.findAll();
		int flag = 0;
		for(Negozio n : neg) {
			if(n.getNome().equals(nomeNegozio)) {
				int x = n.getPresenza();
				n.setPresenza(x-1);
				flag = 1;
			}
			negozi.save(n);
		}
		if(flag == 1)
			return "Presenza tolta";
		else
			return "nome del negozio errato";
	}

	
	
	public List<ProdottoNegozio> ritiroLocker(List<ProdottoNegozio> lista) {
		return locker.ritiroLista(lista);
		
	}
	
	public List<ProdottoNegozio> ritiroNegozio(List<ProdottoNegozio> lista) {
		return commesso.ritiroLista(lista);
		
	}
	
}
