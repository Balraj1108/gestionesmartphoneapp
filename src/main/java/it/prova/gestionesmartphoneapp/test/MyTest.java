package it.prova.gestionesmartphoneapp.test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.model.App;
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
			
			testAggiornamentoVersioneOS(smartphoneServiceInstance);
			
			testInserimentoNuovaApp(appServiceInstance);
			
			testAggiornamentoVersioneApp(appServiceInstance);
			
			testAggiungiAppEdEliminaSmartphone(smartphoneServiceInstance, appServiceInstance);
			
			testEliminaApp(smartphoneServiceInstance, appServiceInstance);
			
			
			
			
			
			
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
	
	private static void testAggiornamentoVersioneOS(SmartphoneService smartphoneServiceInstance) throws Exception{
		System.out.println(".......testAggiornamentoVersioneOS inizio.............");
		
		Smartphone smartphoneTest = new Smartphone("xiaomi", "pro10", 600, "android10");
		smartphoneServiceInstance.insert(smartphoneTest);		
		
		// ora mi salvo da parte le date di creazione ed update
		LocalDateTime createDateTimeIniziale = smartphoneTest.getCreateDateTime();
		LocalDateTime updateDateTimeIniziale = smartphoneTest.getUpdateDateTime();

		// **************************************************************************************************
		// **************************************************************************************************
		// all'inizio DOVREBBERO essere uguali, infatti a volte non lo sono per
		// questione di 10^-4 secondi
		// soluzione: riprovare!!! Se diventa sistematico commentare le due righe
		// successive
		if (!createDateTimeIniziale.equals(updateDateTimeIniziale))
			throw new RuntimeException("testAggiornamentoVersioneOS fallito: le date non coincidono ");
		// **************************************************************************************************
		// **************************************************************************************************

		// ora modifico il record
		smartphoneTest.setVersioneOS("android11");
		smartphoneServiceInstance.update(smartphoneTest);

		// se la nuova data aggiornamento risulta precedente a quella iniziale: errore
		if (smartphoneTest.getUpdateDateTime().isAfter(updateDateTimeIniziale))
			throw new RuntimeException("testAggiornamentoVersioneOS fallito: le date di modifica sono disallineate ");

		// la data creazione deve essere uguale a quella di prima
		if (!smartphoneTest.getCreateDateTime().equals(createDateTimeIniziale))
			throw new RuntimeException("testAggiornamentoVersioneOS fallito: la data di creazione è cambiata ");
		
		
		System.out.println(".......testAggiornamentoVersioneOS fine: PASSED.............");
	}
	
	private static void testInserimentoNuovaApp(AppService appServiceInstance) throws Exception{
		System.out.println(".......testInserimentoNuovaApp inizio.............");
		
		
		Date dateInstallazioneTest = new SimpleDateFormat("dd/MM/yyyy").parse("12/01/2020");
		Date dateAggiornamentoTest = new SimpleDateFormat("dd/MM/yyyy").parse("25/10/2021");
		
		App appTest = new App("facebook", dateInstallazioneTest, dateAggiornamentoTest, "1.2");
		appServiceInstance.insert(appTest);
		
		if (appTest.getId() == null) {
			throw new RuntimeException("Test Inserimento fallito");
		}
		
		
		System.out.println(".......testInserimentoNuovaApp fine: PASSED.............");
	}
	
	private static void testAggiornamentoVersioneApp(AppService appServiceInstance) throws Exception{
		System.out.println(".......testAggiornamentoVersioneApp inizio.............");
	
		Date dateInstallazioneTest = new SimpleDateFormat("dd/MM/yyyy").parse("12/01/2020");
		Date dateAggiornamentoTest = new SimpleDateFormat("dd/MM/yyyy").parse("25/10/2021");
		
		App appTest = new App("google", dateInstallazioneTest, dateAggiornamentoTest, "1.8");
		appServiceInstance.insert(appTest);		

		// ora mi salvo da parte le date di creazione ed update
		LocalDateTime createDateTimeIniziale = appTest.getCreateDateTime();
		LocalDateTime updateDateTimeIniziale = appTest.getUpdateDateTime();

		if (!createDateTimeIniziale.equals(updateDateTimeIniziale))
			throw new RuntimeException("testAggiornamentoVersioneApp fallito: le date non coincidono ");
		// **************************************************************************************************
		// **************************************************************************************************

		// ora modifico il record
		appTest.setVersione("2.0");
		appTest.setDataUltimoAggiornamento(new Date());
		appServiceInstance.update(appTest);

		// se la nuova data aggiornamento risulta precedente a quella iniziale: errore
		if (appTest.getUpdateDateTime().isAfter(updateDateTimeIniziale))
			throw new RuntimeException("testAggiornamentoVersioneApp fallito: le date di modifica sono disallineate ");

		// la data creazione deve essere uguale a quella di prima
		if (!appTest.getCreateDateTime().equals(createDateTimeIniziale))
			throw new RuntimeException("testAggiornamentoVersioneApp fallito: la data di creazione è cambiata ");		
		
		
		System.out.println(".......testAggiornamentoVersioneApp fine: PASSED.............");
		
	}
	
	private static void testAggiungiAppEdEliminaSmartphone(SmartphoneService smartphoneServiceInstance,
			AppService appServiceInstance ) throws Exception{
		System.out.println(".......testAggiungiApp inizio.............");
		
		Smartphone smartphoneTest = new Smartphone("apple", "8s", 1500, "ios10");
		smartphoneServiceInstance.insert(smartphoneTest);
		
		Date dateInstallazioneTest = new SimpleDateFormat("dd/MM/yyyy").parse("12/01/2020");
		Date dateAggiornamentoTest = new SimpleDateFormat("dd/MM/yyyy").parse("25/10/2021");
		
		App appTest = new App("instagram", dateInstallazioneTest, dateAggiornamentoTest, "2.8");
		appServiceInstance.insert(appTest);	
		App appTest1 = new App("gmail", dateInstallazioneTest, dateAggiornamentoTest, "3.8");
		appServiceInstance.insert(appTest1);	
		
		smartphoneServiceInstance.aggiungiApp(smartphoneTest, appTest);
		smartphoneServiceInstance.aggiungiApp(smartphoneTest, appTest1);
		
		
		// ricarico eager per forzare il test
		Smartphone smartphoneReloaded = smartphoneServiceInstance.caricaSingoloElementoEagerApps(smartphoneTest.getId());
		if (smartphoneReloaded.getApps().isEmpty())
			throw new RuntimeException("testCreazioneECollegamentoCdInUnSoloColpo fallito: genere e cd non collegati ");
		
		
		smartphoneServiceInstance.delete(smartphoneTest.getId());
		appServiceInstance.delete(appTest.getId());
		appServiceInstance.delete(appTest1.getId());
		
		
		System.out.println(".......testAggiungiApp fine: PASSED.............");
	}
	
	private static void testEliminaApp(SmartphoneService smartphoneServiceInstance,
			AppService appServiceInstance ) throws Exception{
		
		System.out.println(".......testEliminaApp inizio.............");
		
		Smartphone smartphoneTest = new Smartphone("huawei", "P20pro", 1500, "android15");
		smartphoneServiceInstance.insert(smartphoneTest);
		
		Date dateInstallazioneTest = new SimpleDateFormat("dd/MM/yyyy").parse("12/01/2020");
		Date dateAggiornamentoTest = new SimpleDateFormat("dd/MM/yyyy").parse("25/10/2021");
		
		App appTest = new App("youtube", dateInstallazioneTest, dateAggiornamentoTest, "2.8");
		appServiceInstance.insert(appTest);		
		
		smartphoneServiceInstance.aggiungiApp(smartphoneTest, appTest);
		
		
		//smartphoneServiceInstance.delete(smartphoneTest.getId());
		appServiceInstance.disassociaAppDaTelefono(appTest.getId());
		
		Smartphone smartphoneTestControlloDisassociaApp = smartphoneServiceInstance
				.caricaSingoloElementoEagerApps(smartphoneTest.getId());
		if (smartphoneTestControlloDisassociaApp.getApps().size() != 0) {
			throw new RuntimeException("Test Fallito: disassociamentoApp");
		}
		
		appServiceInstance.delete(appTest.getId());
		
		System.out.println(".......testEliminaApp fine: PASSED.............");
	}

}
