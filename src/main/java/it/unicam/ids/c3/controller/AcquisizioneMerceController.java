package it.unicam.ids.c3.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.ids.c3.entity.Negozio;
import it.unicam.ids.c3.Repository.IndirizziNegoziRepository;
import it.unicam.ids.c3.entity.ProdottoIngrosso;
import it.unicam.ids.c3.entity.ProdottoNegozio;
import it.unicam.ids.c3.service.CommessoService;
import it.unicam.ids.c3.service.CorriereService;


@RestController
public class AcquisizioneMerceController {
//	@Autowired
//	IndirizziNegoziRepository negozio;
	
	@Autowired
	CorriereService corriere;
	
	@Autowired
	CommessoService commesso;
	
	List<ProdottoNegozio> listaProdottiRitiro = new ArrayList<ProdottoNegozio>();
	
	@GetMapping("/indirizzo/{nomeNegozio}")
	public String getIndirizzo( @PathVariable("nomeNegozio") String nomeNegozio) {
		return corriere.getOneAddres(nomeNegozio);			
	}
	
	
	@PostMapping("/prelevaProdotti")
	public List<ProdottoNegozio> prelevaProdotti(@RequestBody ListaRitiro prodottiListaOrdine){
		return corriere.prelevaProdotti(prodottiListaOrdine.prodottiListaRitiro);
	}
	
	//setter e getter per la lista creata
	public List<ProdottoNegozio> getListaProdottiRitiro() {
		return listaProdottiRitiro;
	}

	public void setListaProdottiRitiro(List<ProdottoNegozio> listaProdottiRitiro) {
		this.listaProdottiRitiro = listaProdottiRitiro;
	}
	
	
}
class ListaRitiro{
	public ArrayList<ProdottoNegozio> prodottiListaRitiro;
}


