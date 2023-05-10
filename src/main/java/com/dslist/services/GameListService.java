package com.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dslist.dto.GameListDTO;
import com.dslist.entities.GameList;
import com.dslist.repositories.GameListRepository;

@Service
public class GameListService {
	
	@Autowired
	private GameListRepository gameListRepository;

	@Transactional(readOnly = true)
	public List<GameListDTO> findAll() {

		List<GameList> list = gameListRepository.findAll();
		return list.stream().map(x -> new GameListDTO(x)).toList();
	}

}
