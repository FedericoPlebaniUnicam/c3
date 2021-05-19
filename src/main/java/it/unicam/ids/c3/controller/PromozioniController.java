package it.unicam.ids.c3.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.ids.c3.Repository.FornitoreRepository;
import it.unicam.ids.c3.entity.ProdottoIngrosso;
import it.unicam.ids.c3.entity.ProdottoNegozio;
import it.unicam.ids.c3.service.ClienteService;
import it.unicam.ids.c3.service.CommercianteService;



@RestController
public class PromozioniController {

	
	@Autowired
	CommercianteService commerciante;
	@Autowired
	ClienteService cliente;
	
	@PostMapping("/LanciaPromozioni")
	public List<ProdottoNegozio> lancioPromozione( @RequestParam String categoria, @RequestParam double sconto){
		
		return commerciante.lancioPromozioni(categoria,sconto);
	}
	
	@PostMapping("/CancellaPromozioni")
	public String cancellaPromozioni(@RequestParam String categoria) {
		return commerciante.cancellaPromozioni(categoria);
	}
	
	@PostMapping("/filtroPromozioni")
	public List<ProdottoNegozio> filtroPromozioni(@RequestParam String categoria){
		return cliente.filtroPromozioni(categoria);
	}
}
