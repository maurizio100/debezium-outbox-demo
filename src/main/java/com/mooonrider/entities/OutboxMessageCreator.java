package com.mooonrider.entities;

import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mooonrider.outbox.OutboxPayload;

@ApplicationScoped
public class OutboxMessageCreator {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private ObjectMapper objectMapper;
	
	public void createOutboxMessage(String event, OutboxPayload payload) {
    	Outbox outbox = new Outbox();
    	outbox.setAggregateId("1111");
    	outbox.setAggregateType("CharacterWithGames");
    	outbox.setTimestamp(new Date().getTime());    	
    	outbox.setType("Character");
    	outbox.setEvent("CharacterCreated");
    	
    	try {
			String jsonPayload = objectMapper.writeValueAsString(payload);
			outbox.setPayload(jsonPayload);
    	
			entityManager.persist(outbox);
			entityManager.remove(outbox);
    	} catch (JsonProcessingException e) { }
	}
}
