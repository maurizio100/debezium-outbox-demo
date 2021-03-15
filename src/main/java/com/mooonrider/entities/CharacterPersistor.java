package com.mooonrider.entities;

import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.mooonrider.outbox.CharacterInformation;

@ApplicationScoped
public class CharacterPersistor {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private OutboxMessageCreator outboxMessageCreator;
	
	@Transactional
	public void saveCharacter() {
    	Character c = createCharacter();
    	entityManager.persist(c);
    	
    	CharacterInformation information = 
    			new CharacterInformation(
    					c.getId(), 
    					c.getName(), 
    					c.getGames().stream().map(g -> g.getTitle()).collect(Collectors.toList())
    			);
    	
    	outboxMessageCreator.createOutboxMessage("CharacterCreated", information);
	}
	
	private Character createCharacter() {
		Game g = new Game();
    	g.setTitle("Sonic the Hedgehog");
    	
    	Character c = new Character();
    	c.setName("Sonic");
    	c.setGames(Set.of(g));
    	
    	return c;
	}
	
}
