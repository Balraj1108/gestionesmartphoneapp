package it.prova.gestionesmartphoneapp.service;

import java.util.List;

import it.prova.gestionesmartphoneapp.dao.app.AppDAO;
import it.prova.gestionesmartphoneapp.model.App;

public interface AppService {

	public List<App> list() throws Exception;
	
	public App get(Long id) throws Exception;
	
	public void update(App appInstance) throws Exception;
	
	public void insert(App appInstance) throws Exception;
	
	public void delete(App appInstance) throws Exception;

	
	
	
	public void setAppDAO(AppDAO appDAO);
	
}
