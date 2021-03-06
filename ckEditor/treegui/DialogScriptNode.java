package ckEditor.treegui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SpinnerNumberModel;

import ckGameEngine.QuestData;
import ckGraphicsEngine.CKGraphicsEngine;

public class DialogScriptNode extends ScriptNode
{

	private static final long serialVersionUID = 3716370123643260649L;

	@Override
	public void initScript(QuestData quest, CKGraphicsEngine engine)
	{
		engine.loadDialogDebugScript(getScript(), 60);
		
	}

	
	@Override
	public void createScript(QuestData quest, CKGraphicsEngine engine)
	{
		setScript("");
		engine.loadDialogLogListener(this);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.tree.DefaultMutableTreeNode#toString()
	 */
	@Override
	public String toString()
	{
		return "Dialog Script Node:"+getName();
	}
	
	
	private final static Icon dialogIcon = new ImageIcon("ckEditor/images/tools/dialog.png");
	/* (non-Javadoc)
	 * @see ckGraphics.treegui.CKGUINode#getTreeIcon(boolean, boolean)
	 */
	@Override
	public Icon getTreeIcon(boolean leaf, boolean expanded)
	{
		
		return dialogIcon;
	}

	
	static JPanel []panel;
	static JTextField[] nameFields;
	static SpinnerNumberModel[] priorityBox;
	static JTextArea[] scriptBox;

	

	
	private static void initPanel(boolean force)
	{
		if(panel==null || force)
		{
			panel=new JPanel[2];
			panel[0]=new JPanel();
			panel[1]=new JPanel();			

			panel[0].setLayout(new BoxLayout(panel[0], BoxLayout.PAGE_AXIS));
			panel[1].setLayout(new BoxLayout(panel[1], BoxLayout.PAGE_AXIS));

			panel[0].setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
			panel[1].setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
			
			JPanel []tops=new JPanel[2];
			tops[0]=new JPanel();
			tops[1]=new JPanel();
			
			tops[0].setLayout(new BoxLayout(tops[0], BoxLayout.LINE_AXIS));
			tops[0].setBackground(colors[0]);
			tops[1].setBackground(colors[1]);
			tops[1].setLayout(new BoxLayout(tops[1], BoxLayout.LINE_AXIS));
			
			
			//name
			nameFields = new JTextField[2];
			nameFields[0]=new JTextField(15);
			nameFields[1]=new JTextField(15);
			tops[0].add(nameFields[0]);
			tops[1].add(nameFields[1]);

			tops[0].add(new JLabel(" w/ priority "));
			tops[1].add(new JLabel(" w/ priority "));
			

			
			priorityBox=new SpinnerNumberModel[2]; 
			priorityBox[0] = new SpinnerNumberModel(1, 0, 100,1);
			priorityBox[1] = new SpinnerNumberModel(1, 0, 100,1);
			JSpinner spin = new JSpinner(priorityBox[0]);
			tops[0].add(spin);
			spin = new JSpinner(priorityBox[1]);
			tops[1].add(spin);
			
			panel[0].add(tops[0]);
			panel[1].add(tops[1]);
		


			JPanel []bot=new JPanel[2];
			bot[0]=new JPanel();
			bot[1]=new JPanel();
			
			bot[0].setLayout(new BoxLayout(bot[0], BoxLayout.LINE_AXIS));
			bot[1].setLayout(new BoxLayout(bot[1], BoxLayout.LINE_AXIS));
			bot[0].setBackground(colors[0]);
			bot[1].setBackground(colors[1]);

			
			//starts at 			
			
			scriptBox = new JTextArea[2];
			scriptBox[0] = new JTextArea(3,15);
			scriptBox[1] = new JTextArea(3,15);
/*
			scriptBox[0].setLineWrap(true);
			scriptBox[0].setWrapStyleWord(true);
			scriptBox[1].setLineWrap(true);
			scriptBox[1].setWrapStyleWord(true);
*/
			
			bot[0].add(scriptBox[0]);
			bot[1].add(scriptBox[1]);
			panel[0].add(bot[0]);
			panel[1].add(bot[1]);
		}
		
	}
	
	private final static int EDIT=0;
	private final static int RENDER=1;
	private final static Color[] colors={Color.GREEN,Color.ORANGE};
	
	private void setPanelValues(int index)
	{
		//System.out.println("setting panel");
		if(panel==null) { initPanel(true); }
		panel[index].setBackground(colors[index]);
		
		nameFields[index].setText(getName());
		priorityBox[index].setValue(getPriority());
		scriptBox[index].setText(getScript());
		
		panel[index].validate();
	}
	
	
	/* (non-Javadoc)
	 * @see ckGraphics.treegui.CKGUINode#getTreeCellEditorComponent(javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int)
	 */
	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row)
	{
		setPanelValues(EDIT);
		return panel[EDIT];	
	}

	/* (non-Javadoc)
	 * @see ckGraphics.treegui.CKGUINode#storeComponentValues()
	 */
	@Override
	public void storeComponentValues()
	{
		
		setName(nameFields[EDIT].getText());
		setPriority( (Integer) priorityBox[EDIT].getNumber());
		setScript(scriptBox[EDIT].getText()) ;
			
	}

	/* (non-Javadoc)
	 * @see ckGraphics.treegui.CKGUINode#getTreeCellRendererComponent(javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int, boolean)
	 */
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus)
	{
		setPanelValues(RENDER);
		return panel[RENDER];
	}


	
	

}
