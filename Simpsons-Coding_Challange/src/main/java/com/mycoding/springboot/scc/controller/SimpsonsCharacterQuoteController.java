package com.mycoding.springboot.scc.controller;

import java.util.List;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycoding.springboot.scc.model.AjaxResponseBody;
import com.mycoding.springboot.scc.model.SearchCriteria;
import com.mycoding.springboot.scc.model.SimpsonsCharacter;
import com.mycoding.springboot.scc.services.SimpsonsCharacterQuoteService;

@RestController
public class SimpsonsCharacterQuoteController {

	SimpsonsCharacterQuoteService simpsonsCharacterQuoteService;

	@Autowired
	public void setUserService(SimpsonsCharacterQuoteService simpsonsCharacterQuoteService) {
		this.simpsonsCharacterQuoteService = simpsonsCharacterQuoteService;
	}

	@GetMapping("/api/getAllCharaters")
	public List getAllUsers() {

		List<SimpsonsCharacter> simpsonsCharacters = simpsonsCharacterQuoteService.getAllSimpsonCharacter();
		return simpsonsCharacters;
	}

	@GetMapping("/{id}")
	public ResponseEntity<SimpsonsCharacter> getSimpsonsCharacterById(@PathVariable("id") Long id) {
		SimpsonsCharacter entity = simpsonsCharacterQuoteService.getSimpsonsCharacterById(id);
		return new ResponseEntity<SimpsonsCharacter>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/api/save")
	public List createOrUpdateSimpsonsCharacter(@Valid @RequestBody SimpsonsCharacter simpsonsCharacter) {
		List<SimpsonsCharacter> simpsonsCharacters = simpsonsCharacterQuoteService.saveNewCharacter(simpsonsCharacter);
		return simpsonsCharacters;

	}

	@DeleteMapping("/api/delete/{id}")
	public HttpStatus deleteSimpsonsCharacterById(@PathVariable("id") Long id) {
		AjaxResponseBody result = new AjaxResponseBody();
		result.setMsg(simpsonsCharacterQuoteService.deleteSimpsonsCharacterById(id));
	   return HttpStatus.OK;
	}

	@PostMapping("/api/search")
	public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody SearchCriteria search, Errors errors) {

		AjaxResponseBody result = new AjaxResponseBody();

		// If error, just return a 400 bad request, along with the error message
		if (errors.hasErrors()) {

			result.setMsg(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
			return ResponseEntity.badRequest().body(result);

		}

		List<SimpsonsCharacter> simpsonsCharacters = simpsonsCharacterQuoteService
				.findByFirstNameOrLastName(search.getFirstName(), search.getLastName());
		if (simpsonsCharacters.isEmpty()) {
			result.setMsg("no Simpsons Character found!");
		} else {
			result.setMsg("success");
		}
		result.setResult(simpsonsCharacters);
	
		return ResponseEntity.ok(result);

	}

}
