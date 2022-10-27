package it.prova.gestionesmartphoneapp.service;

import java.util.List;

import it.prova.gestionesmartphoneapp.dao.smartphone.SmartphoneDAO;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;


public interface SmartphoneService {


	public List<Smartphone> list() throws Exception;
	
	public Smartphone get(Long id) throws Exception;
	
	public void update(Smartphone smartphoneInstance) throws Exception;
	
	public void insert(Smartphone smartphoneInstance) throws Exception;
	
	public void delete(Long idSmartphone) throws Exception;
	
	public void aggiungiApp(Smartphone smartphoneInstance, App appInstance) throws Exception;
	
	public Smartphone caricaSingoloElementoEagerApps(Long id) throws Exception;

	
	
	public void setSmartphoneDAO(SmartphoneDAO smartphoneDAO);
	
}
