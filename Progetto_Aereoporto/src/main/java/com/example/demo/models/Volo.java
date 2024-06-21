package com.example.demo.models;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "voli")
public class Volo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Volo() {}
	
	@Id
	@Column(name = "codice_volo_id")
	private int codiceVoloID;
	
	@Column(name = "nome_volo")
	private String nomeVolo;
	
	@Column(name = "aereoporto_partenza")
	private String aereoportoPartenza;
	
	@Column(name = "costo_volo")
	private float costoVolo;
	
	@Column(name = "data_volo")
	private Date dataVolo;
	
	@Column(name = "ora_partenza")
	private Time oraPartenza;
	
	@Column(name = "posti_disponibili")
	private int postiDisponibili;
}
