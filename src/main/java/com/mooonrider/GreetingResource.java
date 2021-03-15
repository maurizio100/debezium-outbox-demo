package com.mooonrider;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mooonrider.entities.CharacterPersistor;

@Path("/hello-resteasy")
public class GreetingResource {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private CharacterPersistor characterPersistor;
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {    	
    	characterPersistor.saveCharacter();
    	return "persisted";
    }
}