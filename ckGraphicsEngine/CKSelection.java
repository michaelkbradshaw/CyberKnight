package ckGraphicsEngine;

import java.util.Collection;

import ckCommonUtils.CKPosition;
import ckGameEngine.CKGameObjectsFacade;
import ckGraphicsEngine.CKGraphicsEngine.SelectAreaType;

public class CKSelection implements CKSelectedPositionsListeners
{
	
	boolean ready = true;
	//CKPosition[] positions;
	CKPosition positions;

	public CKSelection()
	{
		
	}
	
	public synchronized CKPosition SelectTarget(CKPosition originLocation, float minDistance,float maxDistance)
	{
		
		while(! ready)
		{
			try { 	wait();  }
			catch (InterruptedException e) { }		
		}
		ready = false;
		positions=null;
		CKGameObjectsFacade.getEngine().selectArea(originLocation, minDistance, maxDistance,
					this, SelectAreaType.TARGET);
		try { 	wait(); }
		catch (InterruptedException e) {}
//		{
//			ready=true;
//			return positions[0];
//		}
		ready = true;
		return positions;
		
	}

	public synchronized CKPosition SelectTargetArea(CKPosition originLocation, 
			float minDistance,float maxDistance,Collection <CKPosition>offsets)
	{
		
		while(! ready)
		{
			try { 	wait();  }
			catch (InterruptedException e) { }		
		}
		ready = false;
		positions=null;
		CKGameObjectsFacade.getEngine().selectAreaOffsets(originLocation, minDistance, maxDistance,
					this,offsets );
		try { 	wait(); }
		catch (InterruptedException e) {}

		ready = true;
		return positions;
		
	}

	
	@Override
	public synchronized void NotifyOfTargets(CKPosition pos)
	{
		positions = pos;
		this.notifyAll();
		
	}
	
	
}
