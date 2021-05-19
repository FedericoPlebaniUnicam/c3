package it.unicam.ids.c3.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.ids.c3.entity.Negozio;
import it.unicam.ids.c3.entity.ProdottoIngrosso;
import it.unicam.ids.c3.entity.ProdottoNegozio;
import it.unicam.ids.c3.service.CommercianteService;
import it.unicam.ids.c3.service.CorriereService;


@RestController
public class TrasportoMerceController {
	
	@Autowired
	CorriereService corriere;
		

	
	@GetMapping("/destinazioneNegozio")
	public String getDestinazioneNegozio(@PathVariable("nomeNegozio") String nome) {
		
		return corriere.getDestinazioneNegozio(nome); 
		
	}
	
	@GetMapping("/destinazioneLocker/{nomeLocker}")
	public String getDestinazioneLocker(@PathVariable("nomeLocker") String nome) {
		
		return corriere.getDestinazioneLocker(nome); 
		
	}
	@GetMapping("/destinazioneDomicilio/{nomeDomicilio}")
	public String getDestinazioneDomicilio(@PathVariable("nomeDomicilio") String nome) {
		
		return corriere.getDestinazioneDomicilio(nome); 
		
	}
//___________________________________________________________________________________________________
	
	@PostMapping("/rilasciaMerceLocker")
	public String rilasciaMerceLocker(@RequestBody ListaTrasporto prodottiTrasporto) {
		return corriere.rilasciaMerceLocker(prodottiTrasporto.prodotti);
	}
	
	@PostMapping("/rilasciaMerceNegozio")
	public String rilasciaMerceNegozio(@RequestBody ListaTrasporto prodottiTrasporto) {
		return corriere.rilasciaMerceNegozio(prodottiTrasporto.prodottiListaconsegna);
	}
	
	@PostMapping("/rilasciaMerceDomicilio")
	public String rilasciaMerceDomicilio(@RequestBody ListaTrasporto prodottiTrasporto) {
		return corriere.rilasciaMerceDomicilio(prodottiTrasporto.prodotti);
	}
}
class ListaTrasporto{
	public ArrayList<ProdottoNegozio> prodotti;
	public ArrayList<ProdottoIngrosso> prodottiListaconsegna;

}
