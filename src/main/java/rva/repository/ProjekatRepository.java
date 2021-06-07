package rva.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.jpa.Projekat;
//JpaREpository ima ugradjenje metode za izlistavanje, selektovanje projektoavanj, po idu i sl...
public interface ProjekatRepository extends JpaRepository <Projekat, Integer>{
		
	Collection<Projekat> findByNazivContainingIgnoreCase(String naziv); //ovde se mora postovati naziv!!!
}
