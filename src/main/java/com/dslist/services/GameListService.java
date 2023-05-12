package com.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dslist.dto.GameListDTO;
import com.dslist.entities.GameList;
import com.dslist.projections.GameMinProjection;
import com.dslist.repositories.GameListRepository;
import com.dslist.repositories.GameRepository;

@Service
public class GameListService {
	
	@Autowired
	private GameListRepository gameListRepository;
	
	@Autowired
	private GameRepository gameRepository;

	@Transactional(readOnly = true)
	public List<GameListDTO> findAll() {

		List<GameList> list = gameListRepository.findAll();
		return list.stream().map(x -> new GameListDTO(x)).toList();
	}
	
	@Transactional
	public void move(Long id, int sourceIndex, int destinationIndex) {
		List<GameMinProjection> list = gameRepository.searchByList(id);
		GameMinProjection obj = list.remove(sourceIndex);
		list.add(destinationIndex, obj);
		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;
		for (int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(id, list.get(i).getId(), i);
		}
	}

}
