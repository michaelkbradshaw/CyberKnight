package ckEditor;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;

import ckEditor.treegui.CKCasterEffect;
import ckEditor.treegui.CKTreeGui;

public class CKCasterEffectAddMenu
{
	public static JMenu notSupported(String text)
	{
		JMenu addActions = new JMenu(text);
		addActions.setEnabled(false);
		return addActions;
	}
	
	public static JMenu getMenu(CKTreeGui tree)
	{
		return getMenu(tree,"Add Caster Effect",0,false);
	}
	
	public static JMenu getMenu(CKTreeGui tree,String text,int pos, boolean replace)
	{
		JMenu addActions = new JMenu(text);
			/*        Add Actions --should these be based on the item?*/
			JMenuItem addSeq = new JMenuItem("Add background effect");
			addSeq.addActionListener(new TreeAddActionListener("BKG",tree,pos,replace));
			addActions.add(addSeq);

			return addActions;
		}
	
	


	
static class TreeAddActionListener implements ActionListener
{
		String name;
		String hero = "HERO";
		CKTreeGui tree;
		int position;
		boolean replace;
		
		public TreeAddActionListener(String n,CKTreeGui t,int pos,boolean replace)
		{
			name = n;
			tree=t;
			position = pos;
			this.replace=replace;
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{
			DefaultMutableTreeNode	node	= tree.getSelected();
			
			if(node == null)
			{
			
				return;
			}
			CKCasterEffect action=new CKCasterEffect();
			//if(name.compareTo("BKG")==0)
			//{
					//do nothing already default
			//}

			if(replace)
			{
				node.remove(position);
			}
			
			tree.addNode(node,action,position);		
		}
			
	
}


}
