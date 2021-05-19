package it.unicam.ids.c3.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.unicam.ids.c3.entity.ProdottoIngrosso;

@Repository
public interface FornitoreRepository extends JpaRepository<ProdottoIngrosso,String> {

}
