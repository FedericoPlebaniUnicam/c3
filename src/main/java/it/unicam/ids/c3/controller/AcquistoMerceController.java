package it.unicam.ids.c3.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.ids.c3.Repository.FornitoreRepository;
import it.unicam.ids.c3.entity.ProdottoIngrosso;
import it.unicam.ids.c3.service.CommercianteService;

//import it.unicam.ids.c3.Utility.Ordine;

@RestController
public class AcquistoMerceController {
	
//	@Autowired
//	FornitoreRepository repository;
	
	@Autowired
	CommercianteService commerciante;
	
	
	
	
	
	@PostMapping("/crealista")
	public List<ProdottoIngrosso> creaLista( @RequestBody Lista prodottiListaOrdine ){
		List<ProdottoIngrosso> c;
		c = commerciante.inviaLista(prodottiListaOrdine.prodottiListaOrdine);
		return c;
	}
	
	
	
}

class Lista{
	public ArrayList<ProdottoIngrosso> prodottiListaOrdine;
}
