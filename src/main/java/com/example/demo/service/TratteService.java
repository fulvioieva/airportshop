package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Tratte;
import com.example.demo.repository.TratteRepository;

@Service
public class TratteService implements ITratteService{

	@Autowired
	private TratteRepository tratteRepository;

	@Override
	public boolean addTratte(Tratte tratta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTratte(Tratte tratta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Tratte> getAllTratte() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Tratte> getTratte(int idTratta) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean deleteTratte(int idTratta) {
		// TODO Auto-generated method stub
		return false;
	}
}
