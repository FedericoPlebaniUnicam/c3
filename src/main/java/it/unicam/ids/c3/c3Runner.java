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
			product.save( new ProdottoIngrosso("alimentari", "prosciutto", 3.15, 3, 0.0));
			product.save( new ProdottoIngrosso("vestiario", "jeans", 30.00, 6, 0.0));
			product.save( new ProdottoIngrosso("alimentari", "mortadella", 3.15, 4, 0.0));
			product.save( new ProdottoIngrosso("alimentari", "lonza", 3.15, 6, 0.0));
			product.save( new ProdottoIngrosso("vestiario", "t-shirt", 15.15, 8, 0.0));
			
			negozio.save( new Negozio("corridoio8" , "vestiario","via Castello 33", "63822", "civitanova", "italia",0));
			negozio.save( new Negozio("Alimentari Franco" ,"Alimentare", "Corso Dalmazia 34", "63822", "civitanova", "italia", 0));
			locker.save(new Locker("Bartolini Magazzini" , "via Castello 33", "63822", "civitanova", "italia"));
			locker.save(new Locker("Poste Italiane" , "via Castello 33", "63822", "civitanova", "italia"));
			domicilio.save(new Indirizzo("Edoardo Mulinari" , "via Crisalide 33", "63822", "civitanova", "italia"));
			domicilio.save(new Indirizzo("Federico Plebani" , "via Cristore 33", "63822", "civitanova", "italia"));
			
			
			prodotti.save(new ProdottoNegozio("vestiario", "t-shirt", 15.15, 8, 15.0, LocalDate.of(2022, 01, 13)));
			prodotti.save(new ProdottoNegozio("vestiario", "cappotto", 150.50, 8, 0.0, LocalDate.of(2022, 01, 13)));
			prodotti.save(new ProdottoNegozio("vestiario", "jeans", 40.00, 4, 30.0, LocalDate.of(2022, 01, 13)));
			prodotti.save(new ProdottoNegozio("casalingo", "vetril", 5.15, 18, 0.0 ,LocalDate.of(2022, 01, 13)));
			prodotti.save(new ProdottoNegozio("casalingo", "amuchina", 2.15, 28, 0.0, LocalDate.of(2022, 02, 13)));
			prodotti.save(new ProdottoNegozio("vestiario", "nike-air force 1", 101.00, 15, 30.0, LocalDate.of(2022, 01, 13)));
		}
}
