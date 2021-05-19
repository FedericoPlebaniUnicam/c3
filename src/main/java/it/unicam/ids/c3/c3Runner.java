package it.unicam.ids.c3;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.unicam.ids.c3.Repository.FornitoreRepository;
import it.unicam.ids.c3.Repository.IndirizziLockerRepository;
import it.unicam.ids.c3.entity.Indirizzo;
import it.unicam.ids.c3.entity.Locker;
import it.unicam.ids.c3.entity.Negozio;
import it.unicam.ids.c3.Repository.IndirizziNegoziRepository;
import it.unicam.ids.c3.Repository.IndirizziRepository;
import it.unicam.ids.c3.Repository.C3Repository;
import it.unicam.ids.c3.entity.ProdottoIngrosso;
import it.unicam.ids.c3.entity.ProdottoNegozio;

@Component
public class c3Runner implements CommandLineRunner {
	
		@Autowired
		FornitoreRepository product;
		@Autowired
		IndirizziNegoziRepository negozio;
		@Autowired
		IndirizziLockerRepository locker;
		@Autowired
		IndirizziRepository domicilio;
		@Autowired
		C3Repository prodotti;
		
		public void run(String... args) {
			product.save( new ProdottoIngrosso("alimentari", "prosciutto", 3.15, 3));
			product.save( new ProdottoIngrosso("vestiario", "jeans", 30.00, 6));
			product.save( new ProdottoIngrosso("alimentari", "Mozzarella", 3.15, 4));
			product.save( new ProdottoIngrosso("alimentari", "lonza", 3.15, 6));
			product.save( new ProdottoIngrosso("vestiario", "Cappotto", 15.15, 8));
			product.save( new ProdottoIngrosso("alimentari", "lonza", 3.15, 6));
			product.save( new ProdottoIngrosso("cosmetica", "Chanel n°5", 15.15, 8));
			product.save( new ProdottoIngrosso("Ferramenta", "Cacciavite", 3.15, 6));
			product.save( new ProdottoIngrosso("vestiario", "Cappotto", 15.15, 8));
			product.save( new ProdottoIngrosso("alimentari", "Ciauscolo", 3.15, 6));
			product.save( new ProdottoIngrosso("vestiario", "AirForce 1", 15.15, 8));
			
			negozio.save( new Negozio("corridoio8" , "vestiario","via Castello 33", "63822", "civitanova", "italia",0));
			negozio.save( new Negozio("Sephora" , "cosmetica","via Castello 33", "63822", "Macerata", "italia",0));
			negozio.save( new Negozio("Snipes" , "vestiario","via Castello 33", "63822", "Milano", "italia",0));
			negozio.save( new Negozio("Conad" , "Alimentari","via Castello 33", "63822", "Roma", "italia",0));
			negozio.save( new Negozio("OBI" , "Ferramenta","via Castello 33", "63822", "Torino", "italia",0));
			negozio.save( new Negozio("Pandora" , "Gioielleria","via Castello 33", "63822", "Ancona", "italia",0));
			negozio.save( new Negozio("Alimentari Franco" ,"Alimentare", "Corso Dalmazia 34", "63822", "civitanova", "italia", 0));
			
			locker.save(new Locker("Bartolini Magazzini" , "via Castello 33", "63822", "civitanova", "italia"));
			locker.save(new Locker("TNT" , "via Castello 33", "63822", "civitanova", "italia"));
			locker.save(new Locker("Amazon" , "via Castello 33", "63822", "Milano", "italia"));
			locker.save(new Locker("Poste Italiane" , "via Castello 33", "63822", "Roma", "italia"));
			locker.save(new Locker("Amazon" , "via Castello 33", "63822", "Firenze", "italia"));
			locker.save(new Locker("Bartolini" , "via Castello 33", "63822", "Torino", "italia"));
			locker.save(new Locker("TNT" , "via Castello 33", "63822", "Bologna", "italia"));
			
			domicilio.save(new Indirizzo("Edoardo Mulinari" , "via Crisalide 33", "63822", "civitanova", "italia"));
			domicilio.save(new Indirizzo("Federico Plebani" , "via Cristore 33", "63822", "civitanova", "italia"));
			domicilio.save(new Indirizzo("Giorgio Tombolini" , "via Rossi 33", "63822", "Porto Sant'Elpidio", "italia"));
			domicilio.save(new Indirizzo("Marco Rossi" , "via Montenapoleone 22", "63833", "Milano", "italia"));
			domicilio.save(new Indirizzo("Francesco Raspadori" , "via Torino 22", "76736", "Roma", "italia"));
			domicilio.save(new Indirizzo("Roberto Felici" , "via Montagnola 33", "64432", "Firenze", "italia"));
			domicilio.save(new Indirizzo("Mario Rossi" , "via Zanolini 33", "61112", "Bologna", "italia"));
			domicilio.save(new Indirizzo("Roberta Magoni" , "via trieste ", "63822", "Ancona", "italia"));
			
			prodotti.save(new ProdottoNegozio("vestiario", "t-shirt", 15.15, 8, 15.0, LocalDate.of(2020, 01, 13)));
			prodotti.save(new ProdottoNegozio("vestiario", "cappotto", 150.50, 8, 0.0, LocalDate.of(2021, 01, 13)));
			prodotti.save(new ProdottoNegozio("vestiario", "jeans", 40.00, 4, 30.0, LocalDate.of(2022, 03, 13)));
			prodotti.save(new ProdottoNegozio("casalingo", "vetril", 5.15, 18, 0.0 ,LocalDate.of(2022, 05, 13)));
			prodotti.save(new ProdottoNegozio("casalingo", "amuchina", 2.15, 28, 0.0, LocalDate.of(2021, 05, 13)));
			prodotti.save(new ProdottoNegozio("vestiario", "nike-air force 1", 101.00, 15, 30.0, LocalDate.of(2023, 01, 13)));
			prodotti.save(new ProdottoNegozio("cosmetica", "Chanel n°5", 15.15, 8, 15.0, LocalDate.of(2020, 11, 13)));
			prodotti.save(new ProdottoNegozio("ferramenta", "viti", 15.15, 8, 15.0, LocalDate.of(2020, 10, 13)));
			prodotti.save(new ProdottoNegozio("ferramenta", "Cacciavite", 15.15, 8, 15.0, LocalDate.of(2021, 9, 13)));
			prodotti.save(new ProdottoNegozio("gioielleria", "rolex", 1335.0, 8, 15.0, LocalDate.of(2022, 01, 13)));
			prodotti.save(new ProdottoNegozio("alimentri", "Mortadella", 15.15, 8, 15.0, LocalDate.of(2022, 01, 13)));
			prodotti.save(new ProdottoNegozio("vestiario", "t-shirt", 15.15, 8, 15.0, LocalDate.of(2022, 01, 13)));
			prodotti.save(new ProdottoNegozio("cosmetica", "One Millio", 150.15, 8, 15.0, LocalDate.of(2022, 01, 13)));
			prodotti.save(new ProdottoNegozio("vestiario", "maglione", 15.15, 8, 15.0, LocalDate.of(2022, 01, 13)));
		}
}
