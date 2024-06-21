package com.example.demo.models;

import java.io.Serializable;
import java.sql.Date;

import com.example.demo.enums.StatusLogged;
import com.example.demo.enums.TipoPagamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Ticket() {}
	
	@Id
	@Column(name = "ticket_it")
	private int ticketId;
	
	@Column(name = "codice_cliente")
	private int CodiceCliente;
	
	@Column(name = "codice_volo")
	private int codiceVolo;
	
	@Column(name = "quantita")
	private int quantità;
	
	@Column(name = "tipo_pagamento")
	@Enumerated(EnumType.STRING)
	private StatusLogged statusLogged;
	

}
