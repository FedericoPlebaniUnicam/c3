package it.unicam.ids.c3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unicam.ids.c3.entity.Utente;

public interface UtentiRepository extends JpaRepository<Utente,Long> {

}
