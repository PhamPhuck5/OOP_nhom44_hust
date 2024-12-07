import javax.swing.*;

import art.NewGameDialog;
import art.PlayerView;

import java.awt.event.*;
import java.util.*;
/*
 * Jamie Ly
 * jal39@drexel.edu
 * CS ##:TITLE
 * Assignment ## Program ##
 * 
 * Created on May 28, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author DaAznAngel
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ThirteenApplicationMenuBar extends JMenuBar implements ActionListener{
    private static final long serialVersionUID = 1L;//*note

	JCheckBoxMenuItem itemHints, itemHistory;
	JMenu menuNew, menuExit, menuOptions, menuWindows, menuHelp;
	JMenuItem itemRevealHands,itemNew,itemExit;
	JDialog newGame =  new NewGameDialog();
	ThirteenApplication application;
	/**
	 * 
	 */
	public ThirteenApplicationMenuBar(ThirteenApplication app) {
		super();
		application = app;
		//itemExit = new JMenuItem("Exit");
		menuNew = new JMenu("File");
		
		itemNew = new JMenuItem("New");
		menuNew.add(itemNew);
		itemNew.addActionListener(this);
		
		itemExit = new JMenuItem("Exit");
		itemExit.addActionListener(this);
		menuNew.add(itemExit);
		
		//menuExit = new JMenu("Exit");
		
		menuOptions = new JMenu("Options");
		//menuOptions.add(new JCheckBoxMenuItem("Cards Remaining"));
		menuOptions.add(new JCheckBoxMenuItem("Taunting"));
		menuOptions.add(new JCheckBoxMenuItem("Sound Effects"));
		menuOptions.add(new JCheckBoxMenuItem("Music"));
		
		menuWindows = new JMenu("Windows");
		
		menuHelp = new JMenu("Help");
		itemHints = new JCheckBoxMenuItem("Hints");
		itemHints.addActionListener(this);
		itemHints.setState(true);
		menuHelp.add(itemHints);
		
		itemHistory = new JCheckBoxMenuItem("History");
		itemHistory.addActionListener(this);
		itemHistory.setState(true);
		menuHelp.add(itemHistory);
		
		itemRevealHands = new JCheckBoxMenuItem("Reveal Hands");
		itemRevealHands.setActionCommand("Reveal Hands");
		itemRevealHands.addActionListener(this);
		
		menuHelp.add(itemRevealHands);
		menuHelp.addSeparator();
		menuHelp.add(new JMenuItem("About"));
		
		//this.add(itemNew);
		//this.add(itemExit);
		this.add(menuNew);
		//this.add(menuExit);
		this.add(menuOptions);
		//this.add(menuWindows);
		this.add(Box.createHorizontalGlue());
		this.add(menuHelp);
		
			
	}
	
	
	public void actionPerformed(ActionEvent e){
		//JComponent component = e.getSource();
		String type = e.getSource().getClass().getName();
		System.out.println(type);
		if(e.getSource().equals(itemNew)){
			System.out.println("New Game?");
			newGame.setVisible(true);
		}else if(e.getSource() == itemExit){
			System.out.println("Exiting.");
			System.exit(0);
		}else if(e.getSource() == itemRevealHands){
			ArrayList<PlayerView> v = application.getOpponentViews();
			PlayerView p;
			System.out.println("REVEAL!");
			for(int i=0;i<v.size();i++){
				p = (PlayerView) v.get(i);
				//((JCheckBoxMenuItem) e.getSource()).
				p.getHandView().setOpenHand(!p.getHandView().isOpenHand());
				System.out.println(p.getHandView().isOpenHand()?"Open":"Closed");
			}
		}else if(e.getSource() == itemHints){
			System.out.println("Hint Visible: "+application.hintFrame.isVisible());
			application.hintFrame.setVisible(!application.hintFrame.isVisible());
		}else if(e.getSource() == itemHistory){
			System.out.println("History Visible: "+application.historyFrame.isVisible());
			application.historyFrame.setVisible(!application.historyFrame.isVisible());
		}
	}

	public static void main(String[] args) {
		JFrame foo = new JFrame("ThirteenApplicationMenuBar Test");
		foo.setJMenuBar(new ThirteenApplicationMenuBar(null));
		foo.getContentPane().add(new JLabel("TEST"));
		foo.pack();
		foo.setVisible(true);
	}
}
