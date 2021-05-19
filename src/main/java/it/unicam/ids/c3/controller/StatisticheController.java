package it.unicam.ids.c3.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.ids.c3.entity.Negozio;
import it.unicam.ids.c3.Repository.IndirizziNegoziRepository;
import it.unicam.ids.c3.entity.ProdottoIngrosso;
import it.unicam.ids.c3.entity.ProdottoNegozio;
import it.unicam.ids.c3.service.ClienteService;
import it.unicam.ids.c3.service.CommercianteService;
import it.unicam.ids.c3.service.CommessoService;
import it.unicam.ids.c3.service.CorriereService;


@RestController
public class StatisticheController {
	
	@Autowired
	CommercianteService commerciante;
	@Autowired 
	ClienteService cliente;
	
	
	@GetMapping("/Statistiche")
	public int RichiediStatistiche(@RequestParam String nomeNegozio) {
		return commerciante.richiediStatistiche(nomeNegozio);	
	}
	
	@PostMapping("/PresenzaEntrata")
	public String rilevaPresenza(@RequestParam String nomeNegozio) {
		return cliente.rilevaPresenza(nomeNegozio);
	}
	
	@PostMapping("/PresenzaUscita")
	public String RilevaUscita(@RequestParam String nomeNegozio) {
		return cliente.rilevaUscita(nomeNegozio);
	}
}
