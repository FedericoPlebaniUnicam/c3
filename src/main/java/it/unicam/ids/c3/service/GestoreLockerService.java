package it.unicam.ids.c3.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.unicam.ids.c3.Repository.LockerRepository;
import it.unicam.ids.c3.Repository.C3Repository;
import it.unicam.ids.c3.entity.ProdottoNegozio;
import net.bytebuddy.asm.Advice.Local;

@Service
@Component
public class GestoreLockerService {

	@Autowired
	LockerRepository magazzino;
	@Autowired
	C3Repository c3Repository;
	
	
	public List<ProdottoNegozio> scadenzaRitiro(int year, int month , int day) {
		
		List<ProdottoNegozio> prodottiMagazzino = magazzino.findAll();
		List<ProdottoNegozio> prodottiScaduti = new ArrayList<ProdottoNegozio>();
		
		for (/*ProdottoNegozio p : prodottiMagazzino*/ int i = 0; i<prodottiMagazzino.size(); i++) {
			if(prodottiMagazzino.get(i).getDataScadenza().isAfter(LocalDate.of(year, month, day)) == true) {
				prodottiScaduti.add(prodottiMagazzino.get(i));
				prodottiMagazzino.remove(prodottiMagazzino.get(i));
			}
		}
		magazzino.saveAll(prodottiMagazzino);
		return prodottiScaduti;

	}
	

	public String ricezioneProdotti(ArrayList<ProdottoNegozio> prodottiListaOrdine) {
		LocalDate data = LocalDate.now();
		
		for(ProdottoNegozio p : prodottiListaOrdine) {
			p.setDataScadenza(data.plusMonths(2));
			magazzino.save(p);
		}
		
		return "rilascio avvenuto";
	}


	public List<ProdottoNegozio> ritiroLista(List<ProdottoNegozio> lista) {
		List<ProdottoNegozio> lock = magazzino.findAll();
		List<ProdottoNegozio> ret = new ArrayList<ProdottoNegozio>();
		
		for(ProdottoNegozio p : lista) {
			for(ProdottoNegozio l : lock) {
				if(p.getNome().equals(l.getNome()) && p.getQuantita()<= l.getQuantita()) {
					l.setQuantita(l.getQuantita()-p.getQuantita());//setto la quantità del prodotto che devo ritornare
					ret.add(p); 
					
				}	
			}
		}
		
		return ret;
	}
	
}
