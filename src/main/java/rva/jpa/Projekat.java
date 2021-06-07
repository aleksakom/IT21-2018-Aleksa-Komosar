package rva.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the projekat database table.
 * 
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //ignorisemo ono sto necemo da se prikazuje
@Entity //da je jedan projekat jedan entite, jedna torka unutar nase baze
@NamedQuery(name="Projekat.findAll", query="SELECT p FROM Projekat p")
public class Projekat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PROJEKAT_ID_GENERATOR", sequenceName="PROJEKAT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROJEKAT_ID_GENERATOR")
	private Integer id; //sam izgeenerisao na sonvu vrednosti seq- mehanizam kojim se id inkrementira

	private String naziv;

	private String opis;

	private String oznaka;

	//bi-directional many-to-one association to Student
	//Jsonignore da ne dodje do beskanocnog prikazivanja
	@JsonIgnore
	@OneToMany(mappedBy="projekat") //onetomany znaci da se jedan projekat moze odnositi na vise studenata
	private List<Student> students; //odnosi se na jedan projekat

	public Projekat() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getOznaka() {
		return this.oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setProjekat(this);

		return student;
	}

	public Student removeStudent(Student student) {
		getStudents().remove(student);
		student.setProjekat(null);

		return student;
	}

}