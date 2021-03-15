package com.mooonrider.outbox;

import java.util.List;

public class CharacterInformation extends OutboxPayload {

	private final Long id;
	private final String character;
	private final List<String> games;
	
	public CharacterInformation(Long id, String character, List<String> games) {
		this.id = id;
		this.character = character;
		this.games = games;
	}

	public Long getId() {
		return id;
	}

	public String getCharacter() {
		return character;
	}

	public List<String> getGames() {
		return games;
	}
}
