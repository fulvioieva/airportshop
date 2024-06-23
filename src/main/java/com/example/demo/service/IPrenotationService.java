package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Prenotation;

public interface IPrenotationService {
	boolean   addPrenotation(Prenotation prenotation);
	boolean   updatePrenotation (Prenotation prenotation);
	List<Prenotation> getAllPrenotation();
	Optional<Prenotation> getPrenotation(int idPrenotation);
	boolean deletePrenotation(int idPrenotation);
}
