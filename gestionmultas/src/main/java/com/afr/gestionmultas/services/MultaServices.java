package com.afr.gestionmultas.services;

import java.util.List;

import com.afr.gestionmultas.model.Agente;
import com.afr.gestionmultas.model.Multa;

public interface MultaServices {

	/**
	 * 
	 * Se crea una multa con un nuevo c�digo
	 * 
	 * @param multa
	 * @return
	 */
	public Multa create(Multa multa);
	
	public Multa read(Long codigo);
	
	public void delete(Long codigo);
	
	public List<Multa> getAll();
	public List<Multa> getByAgente(Agente agente);
	
	
}
