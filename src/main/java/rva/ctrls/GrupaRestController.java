package rva.ctrls;

import java.util.Collection;

import javax.transaction.Transactional;

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
import rva.jpa.Grupa;
import rva.repository.GrupaRepository;

@CrossOrigin
@RestController
@Api(tags = {"Grupa CRUD operacije"})
public class GrupaRestController {
	
	@Autowired
	private GrupaRepository grupaRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("grupa")
	@ApiOperation(value="Vraća kolekciju svih grupa iz baze podataka")
	public Collection<Grupa> getGrupe(){
		return grupaRepository.findAll();
	}
	
	@GetMapping("grupa/{id}")
	@ApiOperation(value="Vraća grupu na osnovu prosledjenog ID-ja iz baze podataka")
	public Grupa getGrupa(@PathVariable("id") Integer id) {
		return grupaRepository.getOne(id);
	}
	@GetMapping("grupaOznaka/{oznaka}")
	@ApiOperation(value="Vraća kolekciju grupa na osnovu prosledjenog znaka iz baze podataka")
	public Collection<Grupa> getGrupaByOznaka(@PathVariable("oznaka") String oznaka){
		return grupaRepository.findByOznakaContainingIgnoreCase(oznaka); 
	}
	
	@PostMapping("grupa")
	@ApiOperation(value="Upisuje grupu u bazu podataka")
	public ResponseEntity<Grupa> insertGrupa(@RequestBody Grupa grupa){
		if(!grupaRepository.existsById(grupa.getId())) {
			grupaRepository.save(grupa);
			return new ResponseEntity<Grupa>(HttpStatus.OK);
		}
		return new ResponseEntity<Grupa>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("grupa")
	@ApiOperation(value="Update-uje grupu u bazi podataka")
	public ResponseEntity<Grupa> updateGrpa(@RequestBody Grupa grupa){
		if(!grupaRepository.existsById(grupa.getId())) 
			return new ResponseEntity<Grupa>(HttpStatus.NO_CONTENT);
		grupaRepository.save(grupa);
		return new ResponseEntity<Grupa>(HttpStatus.OK);
	}
	
	@Transactional //sve ili nista
	@DeleteMapping("grupa/{id}")
	@ApiOperation(value="Brise grupu na osnovu prosledjenog ID-ja iz baze podataka")
	public ResponseEntity<Grupa> deleteGrupa(@PathVariable Integer id){
		if(!grupaRepository.existsById(id))
			return new ResponseEntity<Grupa>(HttpStatus.NO_CONTENT);
		
		jdbcTemplate.execute(" DELETE FROM student WHERE grupa=" + id);
		grupaRepository.deleteById(id);
		if(id == -100)
			jdbcTemplate.execute(" INSERT INTO \"grupa\" (\"id\", \"oznaka\", \"smer\") "
					+ "VALUES (-100, 'Test Ozn2', 1)"); 
		return new ResponseEntity<Grupa>(HttpStatus.OK);
	}
	
}
