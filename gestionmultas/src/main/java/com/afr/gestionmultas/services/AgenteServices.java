package com.afr.gestionmultas.services;

import java.util.List;

import com.afr.gestionmultas.model.Agente;

public interface AgenteServices {

	public Agente create(Agente agente);
	
	public Agente read(Long codigo);
	public void delete(Long codigo);
	
	public List<Agente> getAll();
	
}
