package com.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dslist.dto.GameDTO;
import com.dslist.dto.GameMinDTO;
import com.dslist.entities.Game;
import com.dslist.projections.GameMinProjection;
import com.dslist.repositories.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;

	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll() {

		List<Game> list = gameRepository.findAll();
		return list.stream().map(x -> new GameMinDTO(x)).toList();
	}

	@Transactional(readOnly = true)
	public GameDTO findById(Long id) {

		Game game = gameRepository.findById(id).get();
		return new GameDTO(game);
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findByList(Long id) {

		List<GameMinProjection> list = gameRepository.searchByList(id);
		return list.stream().map(x -> new GameMinDTO(x)).toList();
	}

}
