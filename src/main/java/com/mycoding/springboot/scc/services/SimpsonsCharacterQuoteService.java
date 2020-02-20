package com.mycoding.springboot.scc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mycoding.springboot.scc.model.SimpsonsCharacter;
import com.mycoding.springboot.scc.repository.SimpsonCharacterRepository;

@Service
public class SimpsonsCharacterQuoteService {

	private List<SimpsonsCharacter> simpsonCharacters;

	@Autowired
	SimpsonCharacterRepository repository;

	public List<SimpsonsCharacter> getAllSimpsonCharacters() {

		simpsonCharacters = repository.findAll();

		List<SimpsonsCharacter> result = simpsonCharacters.stream().collect(Collectors.toList());

		return result;

	}

	public SimpsonsCharacter getSimpsonsCharacterById(Long id) {
		Optional<SimpsonsCharacter> simpsonsCharacter = repository.findById(id);
		return simpsonsCharacter.get();
	}


	public List<SimpsonsCharacter> findByFirstNameOrLastName(String firstName, String lastName) {

		List<SimpsonsCharacter> result = new ArrayList<SimpsonsCharacter>();
		simpsonCharacters = repository.findAll();
		for (SimpsonsCharacter simpsonsCharacter : simpsonCharacters) {

			if ((!StringUtils.isEmpty(firstName)) && (!StringUtils.isEmpty(lastName))) {

				if (firstName.equalsIgnoreCase(simpsonsCharacter.getFirstName())
						&& lastName.equalsIgnoreCase(simpsonsCharacter.getLastName())) {
					result.add(simpsonsCharacter);
					continue;
				} else {
					continue;
				}

			}
			if (!StringUtils.isEmpty(firstName)) {
				if (firstName.equalsIgnoreCase(simpsonsCharacter.getFirstName())) {
					result.add(simpsonsCharacter);
					continue;
				}
			}

			if (!StringUtils.isEmpty(lastName)) {
				if (lastName.equalsIgnoreCase(simpsonsCharacter.getLastName())) {
					result.add(simpsonsCharacter);
					continue;
				}
			}

		}

		return result;

	}

	public List<SimpsonsCharacter> saveCharacterQuote(SimpsonsCharacter entity) {
		List<SimpsonsCharacter> result = new ArrayList<SimpsonsCharacter>();
		result.add(repository.save(entity));
		return result;
	}

	public String deleteSimpsonsCharacterById(Long id) {
		Optional<SimpsonsCharacter> simpsonsCharacter = repository.findById(id);
		String status = "false";
		
		if (simpsonsCharacter.isPresent()) {
			repository.deleteById(id);
			
			status = "true";
		}
	

		return status;
	}

}
