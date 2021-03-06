package ckGameEngine;

import java.util.Vector;

import ckCommonUtils.CKAreaPositions;
import ckCommonUtils.CKPosition;
import ckGameEngine.actions.CKGameAction;
import ckGameEngine.actions.CKGameActionListenerInterface;

public class CKGridItemSet extends CKGridItem implements CKGameActionListenerInterface
{

	Vector<CKAbstractGridItem> items = new Vector<CKAbstractGridItem>();
	Quest qData;
	
	
	public CKGridItemSet()
	{
		
	}
	
	public CKGridItemSet(Vector<CKAbstractGridItem> items)
	{
		this.items=items;
	}
	public CKGridItemSet(CKAreaPositions pos,CKGrid grid,Quest quest)
	{
		this.setName("Area Affect");
		qData = quest;
		this.setPos(pos);
		for(CKPosition p: pos.getArea())
		{	
			if(p instanceof CKAreaPositions)
			{
				items.add(new CKGridItemSet( (CKAreaPositions)p,grid,quest));
			}
			else
			{
				items.add(CKGameObjectsFacade.getQuest().getGrid().getTopPosition(p));
			}
		}
	}
	
	
	/**
	 * @return the items
	 */
	public Vector<CKAbstractGridItem> getItems()
	{
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(Vector<CKAbstractGridItem> items)
	{
		this.items = items;
	}

	
//	private CKGameActionListenerInterface boss;
	
	/* (non-Javadoc)
	 * @see ckGameEngine.CKGridItem#targetSpell(ckGameEngine.actions.CKGameActionListener, ckGameEngine.CKSpellCast)
	 */
	/*@Override
	public void targetSpell(CKGameActionListenerInterface boss, CKSpellCast cast)
	{
		CKGameObjectsFacade.targetSpell(boss, cast);
		
		//TODO should this propogate the spell itself or is it part of SpellAction?
	
	}
*/
	

	@Override
	public void runAction(CKGameAction act, CKSpellCast cast)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionCompleted(CKGameAction action)
	{
		// TODO Auto-generated method stub
		
	}

	
	

}
