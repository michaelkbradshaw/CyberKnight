package ckDatabase;

import javax.swing.JOptionPane;

import ckGameEngine.ActorArtifactController;
import ckGameEngine.ActorController;
import ckGameEngine.ActorNullController;
import ckGameEngine.ActorTurnController;
import ckGameEngine.CKGridActor;
import ckGameEngine.CKGridActorOverLay;
import ckGameEngine.CKGridItem;
import ckGameEngine.OngoingEffectController;

/*
 * Even though this is a factory for CKgridActors, it will return CGridActors
 * 
 * 
 */
public class CKActorControllerFactory extends CKXMLFactory<ActorController>
{
	
	public CKActorControllerFactory()
	{
		this.setShouldReload(true);
	}

	@Override
	public String getBaseDir()
	{
	
		return XMLDirectories.ACTOR_CONTROLLERS_DIR;
	}

	
	private static CKActorControllerFactory factory= null;
	 
	
	 

	public static CKActorControllerFactory getInstance()
	{
		if(factory==null)
		{
			factory = new CKActorControllerFactory();
		}
		return factory;
	}	
	
	
	
	private static void createTestDB()
	{
		
		CKActorControllerFactory factory = CKActorControllerFactory.getInstance();
		
		ActorArtifactController art = new ActorArtifactController();
		art.setPermissions(ActorController.TEXT_CONTROL);
		art.setName("Text Controller");
		art.setAID("TEXT");
		factory.writeAssetToXMLDirectory(art);
				
		art = new ActorArtifactController();
		art.setPermissions(ActorController.ARTIFACT_CONTROL);
		art.setName("Artifact Controller");
		art.setAID("ARTIFACT");
		factory.writeAssetToXMLDirectory(art);
		
		art = new ActorArtifactController();
		art.setPermissions(ActorController.BOTH_CONTROL);
		art.setName("Text and Artifact Controller");
		art.setAID("BOTH");
		factory.writeAssetToXMLDirectory(art);
		
		ActorNullController n = new ActorNullController();
		n.setPermissions(ActorController.NO_CONTROL);
		n.setName("Null Controller");
		n.setAID("NULL");
		factory.writeAssetToXMLDirectory(n);
		
		OngoingEffectController ongoing = new OngoingEffectController();
		ongoing.setPermissions(ActorController.NO_CONTROL);
		ongoing.setName("Ongoing Effect Controller");
		ongoing.setAID("EFFECT");
		factory.writeAssetToXMLDirectory(ongoing);
		
		ActorTurnController turn = new ActorTurnController();
		turn.setPermissions(ActorController.NO_CONTROL);
		turn.setName("Turn Controller");
		turn.setAID("TURN");
		factory.writeAssetToXMLDirectory(turn);
		
		
		
		
		
	}
	
	
	public static void main(String [] args)
	{
		createTestDB();
	}



	@Override
	public ActorController getAssetInstance()
	{
		
		return new ActorNullController();
		

	}
	
	

}
