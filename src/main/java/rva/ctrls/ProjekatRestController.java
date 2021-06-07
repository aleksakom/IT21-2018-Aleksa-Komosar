package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Projekat;
import rva.repository.ProjekatRepository;

@CrossOrigin
@RestController //a
@Api(tags = {"Projekat CRUD operacije"})
public class ProjekatRestController {
	//a
		@Autowired //anotacija za injekciju zavisnosti i to je injektovanje kroz properti, ovde je properti projekatRep logicno, i sad mozemo pozivati sve metode iz jparepository pomocu ovog dole polja
		private ProjekatRepository projekatRepository;
		//ko
		@Autowired
		private JdbcTemplate jdbcTemplate;
		//sada ubacujemo zavisnosti da iz jedne klase pozivamo metode koje se nalaze negde drugo
		//metodama iz jpa pristupamo preko projekaRepositorija - injekotvanje
		
		//getMapping za usmeravanje na browseru i prosledjujemo end point ovoj anotaciji sss
		@GetMapping("projekat")
		@ApiOperation(value="Vraća kolekciju svih projekata iz baze podataka")
		public Collection<Projekat> getProjekti() {
			return projekatRepository.findAll();
		}
		 //uzimam uneseni id i pomocu pathvariable se smesta u id i to se prosledjuje ovom dole propertiju
		@GetMapping("projekat/{id}")
		@ApiOperation(value="Vraća projekat na osnovu prosledjenog ID-ja iz baze podataka")
		public Projekat getProjekat(@PathVariable("id") Integer id) {
			return projekatRepository.getOne(id);
		}
		
		@GetMapping("projekatNaziv/{naziv}")
		@ApiOperation(value="Vraća kolekciju projekata na osnovu unesenog stringa iz baze podataka")
		public Collection<Projekat> getProjekatByNaziv(@PathVariable("naziv") String naziv){
			return projekatRepository.findByNazivContainingIgnoreCase(naziv);
		}
		
		//requestbody cuva objekat pa ga dalje smesta gde vec treba
		@PostMapping("projekat")
		@ApiOperation(value="Dodaje novi projekat u bazu podataka")
		public ResponseEntity<Projekat> insertProjekat(@RequestBody Projekat projekat){
			if(!projekatRepository.existsById(projekat.getId())) {
				projekatRepository.save(projekat);
				return new ResponseEntity<Projekat>(HttpStatus.OK);
			}
			return new ResponseEntity<Projekat>(HttpStatus.CONFLICT);
		}
		
		@PutMapping("projekat")
		@ApiOperation(value="Update-uje projekat iz baze")
		public ResponseEntity<Projekat> updateProjekat(@RequestBody Projekat projekat){
			if(!projekatRepository.existsById(projekat.getId())) 
				return new ResponseEntity<Projekat>(HttpStatus.CONFLICT);
			projekatRepository.save(projekat);
			return new ResponseEntity<Projekat>(HttpStatus.OK);
		}
		
		@DeleteMapping("projekat/{id}")
		@ApiOperation(value="Brise projekat na osnovu prosledjenog ID-ja iz baze")
		public ResponseEntity<Projekat> deleteProjekat(@PathVariable Integer id){
			if(!projekatRepository.existsById(id))
				return new ResponseEntity<Projekat>(HttpStatus.NO_CONTENT);
			projekatRepository.deleteById(id);
			if(id == -100)
				jdbcTemplate.execute(" INSERT INTO \"projekat\" (\"id\", \"naziv\", \"oznaka\", \"opis\") "
						+ "VALUES (-100, 'Test Naz2', 'Test Ozn2', 'Test Op2')"); 
			return new ResponseEntity<Projekat>(HttpStatus.OK);
		}
}
