package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Tratte;

public interface ITratteService {
	boolean   addTratte(Tratte tratta);
	boolean   updateTratte (Tratte tratta);
	List<Tratte> getAllTratte();
	Optional<Tratte> getTratte(int idTratta);
	boolean deleteTratte(int idTratta);
}
