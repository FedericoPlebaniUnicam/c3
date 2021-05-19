package it.unicam.ids.c3.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.ids.c3.entity.Locker;
import it.unicam.ids.c3.entity.ProdottoNegozio;
import it.unicam.ids.c3.service.ClienteService;
import it.unicam.ids.c3.service.CommessoService;

@RestController
public class RitiroMerceController {

	@Autowired
	ClienteService cliente;
	@Autowired
	CommessoService commesso;
	
	
	@GetMapping("/comunicaDomicilio")
	public String checkDomicilio(@RequestParam String nomeCitta, String indirizzo) {
		return commesso.ComunicaDomicilio(nomeCitta, indirizzo);
	}
	@GetMapping("/comunicaPuntoDiPrelievo")
	public List<Locker> getAllLocker(@RequestParam String nomeCitta){
		return commesso.ComunicaPuntoPrelievo(nomeCitta);
	}
	@PostMapping("/RitiroMerceLocker")
	public List<ProdottoNegozio> ritiroLocker(@RequestBody ListaMerceDaRitirare lista){
		return cliente.ritiroLocker(lista.listaRitiro);
	}
	
	@PostMapping("/RitiroMerceNegozio")
	public List<ProdottoNegozio> ritiroNegozio(@RequestBody ListaMerceDaRitirare lista){
		return cliente.ritiroNegozio(lista.listaRitiro);
	}
	
	
}
class ListaMerceDaRitirare{
	public ArrayList<ProdottoNegozio> listaRitiro;
}