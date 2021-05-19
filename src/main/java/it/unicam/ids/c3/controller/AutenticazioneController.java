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

import it.unicam.ids.c3.entity.Utente;
import it.unicam.ids.c3.service.UtenteService;


@RestController
public class AutenticazioneController {
	
	@Autowired
	UtenteService utente;
	
	@PostMapping("/Registrazione")
	public String Registrazione(@RequestBody Utente newUtente) {
		return utente.addNewUser(newUtente);
		
	}
	
	@GetMapping("/Log-in")
	public String LogIn(@RequestParam String username, String pass, String categoria){
		return utente.LogIn(username, pass, categoria);
	}
}
