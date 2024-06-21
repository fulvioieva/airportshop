package com.example.demo.models;

import java.io.Serializable;

import com.example.demo.enums.TipoPagamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clienti")
public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Cliente() {}
	
	@Id
	@Column(name = "codice_cliente_id")
	private int codiceClienteID;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cognome")
	private String cognome;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "eta")
	private int età;
	
	@Column(name = "status_logged")
	@Enumerated(EnumType.STRING)
	private TipoPagamento tipoPagamento;
}
