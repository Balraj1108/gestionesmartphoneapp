package it.prova.gestionesmartphoneapp.service;

import java.util.List;

import it.prova.gestionesmartphoneapp.dao.smartphone.SmartphoneDAO;
import it.prova.gestionesmartphoneapp.model.Smartphone;

public interface SmartphoneService {


	public List<Smartphone> list() throws Exception;
	
	public Smartphone get(Long id) throws Exception;
	
	public void update(Smartphone smartphoneInstance) throws Exception;
	
	public void insert(Smartphone smartphoneInstance) throws Exception;
	
	public void delete(Smartphone smartphoneInstance) throws Exception;

	
	
	public void setSmartphoneDAO(SmartphoneDAO smartphoneDAO);
	
}
