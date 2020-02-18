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

	public List<SimpsonsCharacter> getAllSimpsonCharacter() {

		simpsonCharacters = repository.findAll();

		List<SimpsonsCharacter> result = simpsonCharacters.stream().collect(Collectors.toList());

		return result;

	}

	public SimpsonsCharacter getSimpsonsCharacterById(Long id) {
		Optional<SimpsonsCharacter> simpsonsCharacter = repository.findById(id);
		return simpsonsCharacter.get();
	}

	/*
	 * public List<SimpsonsCharacter> findByFirstNameOrLastName(String firstName) {
	 * 
	 * List<SimpsonsCharacter> result = simpsonCharacters.stream() .filter(x ->
	 * x.getFirstName().equalsIgnoreCase(firstName)).collect(Collectors.toList());
	 * 
	 * return result;
	 * 
	 * }
	 */

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

	public List<SimpsonsCharacter> saveNewCharacter(SimpsonsCharacter entity) {

		//System.out.println("createOrUpdateSimpsonsCharacter entity ID="+entity.getId());
		ArrayList<SimpsonsCharacter> newList = new ArrayList<SimpsonsCharacter>(1);
	
		//Optional<SimpsonsCharacter> simpsonsCharacter = repository.findById(entity.getId());

		/*
		 * if (simpsonsCharacter.isPresent()) {
		 * System.out.println("createOrUpdateSimpsonsCharacter isPresent");
		 * SimpsonsCharacter newEntity = simpsonsCharacter.get();
		 * newEntity.setFirstName(entity.getFirstName());
		 * newEntity.setLastName(entity.getLastName());
		 * newEntity.setPicture(entity.getPicture()); newEntity.setAge(entity.getAge());
		 * 
		 * newEntity = repository.save(newEntity);
		 * 
		 * } else {
		 * System.out.println("createOrUpdateSimpsonsCharacter isPresent else"); entity
		 * = repository.save(entity); }
		 */
		entity = repository.save(entity);
		newList.add(entity);

		return newList;

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
