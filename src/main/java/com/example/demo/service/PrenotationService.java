package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Prenotation;
import com.example.demo.repository.PrenotationRepository;

public class PrenotationService implements IPrenotationService {
	
	@Autowired
	private PrenotationRepository prenotationRepository;

	@Override
	public boolean addPrenotation(Prenotation prenotation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePrenotation(Prenotation prenotation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Prenotation> getAllPrenotation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Prenotation> getPrenotation(int idPrenotation) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean deletePrenotation(int idPrenotation) {
		// TODO Auto-generated method stub
		return false;
	}
}
