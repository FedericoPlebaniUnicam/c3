package it.unicam.ids.c3.controller;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.ids.c3.entity.ProdottoIngrosso;
import it.unicam.ids.c3.entity.ProdottoNegozio;
import it.unicam.ids.c3.service.FornitoreService;


@RestController
public class FornituraMerceController {
	@Autowired
	FornitoreService fornitore;
	
	@PostMapping("/fornituraMerce")
	public String fornisciMerce(@RequestBody Ordine lista ) {
		return fornitore.fornisciMerce(lista.prodottiConsegna);
		 
	}
	
	
}
class Ordine{
	
	public ArrayList<ProdottoIngrosso> prodottiConsegna;
}