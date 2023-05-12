package com.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dslist.dto.GameListDTO;
import com.dslist.dto.GameMinDTO;
import com.dslist.dto.ReplacementDTO;
import com.dslist.services.GameListService;
import com.dslist.services.GameService;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {
	
	@Autowired
	private GameListService gameListService;
	
	@Autowired
	private GameService gameService;
	
	@GetMapping
	public List<GameListDTO> getAll() {
		return gameListService.findAll();
	}
	
	@GetMapping(value = "/{id}/games")
	public List<GameMinDTO> getByList(@PathVariable Long id) {
		return gameService.findByList(id);
	}
	
	@PostMapping(value = "/{id}/replacement")
	public void move(@PathVariable Long id, @RequestBody ReplacementDTO dto) {
		gameListService.move(id, dto.getSourceIndex(), dto.getDestinationIndex());
	}

}
