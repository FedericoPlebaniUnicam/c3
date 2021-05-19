package it.unicam.ids.c3.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.ids.c3.Repository.IndirizziNegoziRepository;
import it.unicam.ids.c3.entity.ProdottoNegozio;
import it.unicam.ids.c3.service.ClienteService;
import it.unicam.ids.c3.service.CommessoService;
import it.unicam.ids.c3.service.CorriereService;

@RestController
public class PagamentoProdottoController {
	
//	@Autowired
//	IndirizziNegoziRepository negozio;
		
	@Autowired
	CommessoService commesso;
	
	@Autowired
	ClienteService cliente;
	
	@PostMapping("/PagamentoTotale")
	public String RichiestaTotale(@RequestBody Carrello carrello) {
		return cliente.getTotale(carrello.listaCarrello);
	}
	
	@PostMapping("/VenditaProdotti")
	public String VenditaProdotti(@RequestBody Carrello prodotti){
		
		return commesso.venditaProdotti(prodotti.listaCarrello);
	}
	
}

class Carrello{
	public ArrayList<ProdottoNegozio> listaCarrello;
}