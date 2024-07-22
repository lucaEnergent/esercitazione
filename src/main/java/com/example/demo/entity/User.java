package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String nome;
	    private String cognome;
	    private LocalDate dataNascita;
	    private String citta;
	    
	    public User() {}
	    
		public User(String nome, String cognome, LocalDate dataNascita, String citta) {
			super();
			this.nome = nome;
			this.cognome = cognome;
			this.dataNascita = dataNascita;
			this.citta = citta;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getCognome() {
			return cognome;
		}

		public void setCognome(String cognome) {
			this.cognome = cognome;
		}

		public LocalDate getDataNascita() {
			return dataNascita;
		}

		public void setDataNascita(LocalDate dataNascita) {
			this.dataNascita = dataNascita;
		}

		public String getCitta() {
			return citta;
		}

		public void setCitta(String citta) {
			this.citta = citta;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita=" + dataNascita
					+ ", citta=" + citta + "]";
		}
	    
}
