package it.prova.gestionesmartphoneapp.test;

import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.model.Smartphone;
import it.prova.gestionesmartphoneapp.service.AppService;
import it.prova.gestionesmartphoneapp.service.MyServiceFactory;
import it.prova.gestionesmartphoneapp.service.SmartphoneService;

public class MyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SmartphoneService smartphoneServiceInstance = MyServiceFactory.getSmartphoneServiceInstance();
		AppService appServiceInstance = MyServiceFactory.getAppServiceInstance();
		
		try {
			
			
			testInsertSmartphone(smartphoneServiceInstance);
			
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			
			EntityManagerUtil.shutdown();
		}
	}
	
	private static void testInsertSmartphone(SmartphoneService smartphoneServiceInstance) throws Exception{
		System.out.println(".......testInsertSmartphone inizio.............");
		
		
		Smartphone smartphoneTest = new Smartphone("samsung", "10x", 500, "android");
		smartphoneServiceInstance.insert(smartphoneTest);
		if (smartphoneTest.getId() == null) {
			throw new RuntimeException("Test Inserimento fallito");
		}
		
		System.out.println(".......testInsertSmartphone fine: PASSED.............");
		
	}

}
