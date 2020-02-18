package com.mycoding.springboot.scc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycoding.springboot.scc.model.SimpsonsCharacter;;
public interface SimpsonCharacterRepository extends JpaRepository<SimpsonsCharacter, Long> {

}
