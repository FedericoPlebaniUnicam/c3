package it.unicam.ids.c3.controller;



import java.sql.Date;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.ids.c3.entity.ProdottoNegozio;
import it.unicam.ids.c3.service.ClienteService;
import it.unicam.ids.c3.service.GestoreLockerService;



@RestController
public class LockerController {
	
	@Autowired
	GestoreLockerService locker;
	@Autowired
	ClienteService cliente;
	
	
	@GetMapping("/Scadenza")
	public List<ProdottoNegozio> scadenzaRitiro(@RequestParam int year,int month, int day ){
		return locker.scadenzaRitiro(year, month , day);
	}
	
	@PostMapping("/RicezioneMerce")
	public String RicezioneProdotti(@RequestBody ProdottiListaRicezione prodotti) {
		return locker.ricezioneProdotti(prodotti.prodottiListaOrdine);
		
	}
	
	@GetMapping("/NotificaPrelievo")
	public String notificaPrelievo(@RequestParam boolean x) {
		return locker.notificaPrelievo(x);
	}
	
}

class ProdottiListaRicezione{
	public ArrayList<ProdottoNegozio> prodottiListaOrdine;
}