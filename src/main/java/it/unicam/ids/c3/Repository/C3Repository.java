package it.unicam.ids.c3.Repository;

	import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.stereotype.Repository;

import it.unicam.ids.c3.entity.ProdottoNegozio;

@Repository
public interface C3Repository extends JpaRepository<ProdottoNegozio,String> {

	

}
