package it.unicam.ids.c3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.unicam.ids.c3.Repository.FornitoreRepository;
import it.unicam.ids.c3.Repository.LockerRepository;
import it.unicam.ids.c3.Repository.UtentiRepository;
import it.unicam.ids.c3.entity.Negozio;
import it.unicam.ids.c3.entity.ProdottoIngrosso;
import it.unicam.ids.c3.Repository.IndirizziNegoziRepository;
import it.unicam.ids.c3.Repository.C3Repository;
import it.unicam.ids.c3.entity.ProdottoNegozio;
import it.unicam.ids.c3.entity.Utente;

@Service
@Component
public class UtenteService {
	
	@Autowired
	UtentiRepository utenti;

	public String addNewUser(Utente utente) {
		utenti.save(utente);
		return "Utente aggiunto con successo";
	}

	public String LogIn(String username, String pass, String categoria) {
		List<Utente> users = utenti.findAll();
		
		for(Utente p : users) {
			if(p.getUsername().equals(username) && p.getPass().equals(pass) && p.getRuolo().equals(categoria)) {
				return "Benvenuto " +p.getNome() +" "+ p.getCognome() + " Buona giornata!";
			}
		}
		return "USERNAME O PASSWORD ERRATE, PER FAVORE RIPROVARE!";
	}

}
