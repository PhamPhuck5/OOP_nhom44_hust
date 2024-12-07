package art;
import javax.swing.*;

import card.Card;
import game.ThirteenGame;
import hand.Hand;
import player.AIPlayer;
import player.Player;

import java.awt.*;
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
public class HintFrame extends JInternalFrame {
    private static final long serialVersionUID = 1L;//*note

	protected ThirteenGame game; protected JLabel label;
	protected JPanel content;
	ImageIcon[] suits = new ImageIcon[4];
	Player p;
	/**
	 * 
	 */
	public HintFrame(ThirteenGame game) {
		super();
		setBackground(Color.PINK);
		setTitle("Hint");
		label = new JLabel("Your next move should be:");
		this.getContentPane().add(label);
		this.game = game;
		this.setLocation(550,0);
		this.setSize(200,200);
		//this.pack();
		this.show();
		try{
			suits[ThirteenGame.SPADE] = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./images/spade.gif"));
			suits[ThirteenGame.CLUB] = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./images/club.gif"));
			suits[ThirteenGame.DIAMOND] = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./images/diamond.gif"));
			suits[ThirteenGame.HEART] = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./images/heart.gif"));  
		} catch(Exception e){
			System.out.println("Could not load suit images.");
			System.exit(1);
		}
		p = new AIPlayer(game,"Computer");
	}
	
	public void updateHint(Hand h){
		System.out.println("Update hint: "+h);
		p.setHand((Hand)h.clone());
		setHand(p.getMove(game));
	}
	
	public void setHand(Hand h){
		setContentPane(getHandPanel(h));
		this.setToolTipText(h.getHandType(h));
	}
	
	public JLabel getCardLabel(Card c){
		JLabel n = new JLabel(c.getValue(),
			getSuitIcon(c.getSuitValue()),SwingConstants.LEFT);
		return n;
	}

	public JPanel getHandPanel(Hand h){
		JPanel outer = new JPanel();
		outer.setBorder(BorderFactory.createLineBorder(Color.black));
		outer.setToolTipText(h.getHandType(h));

		outer.setBackground(Color.PINK);

		if(h.isEmpty()){
			outer.add(new JLabel("You must skip this round."));
			return outer;
		}
		outer.setLayout(new BorderLayout());
		
		JPanel ret = new JPanel();		
		ret.setLayout(new GridLayout(0,5));
		ret.setBackground(Color.PINK);
		
		for(int i=0;i<h.cardCount();i++) ret.add(getCardLabel(h.getCard(i)));
		
		// margins
		outer.add(Box.createRigidArea(new Dimension(5,5)),BorderLayout.NORTH);
		outer.add(Box.createRigidArea(new Dimension(5,5)),BorderLayout.SOUTH);
		outer.add(Box.createRigidArea(new Dimension(5,5)),BorderLayout.EAST);
		outer.add(Box.createRigidArea(new Dimension(5,5)),BorderLayout.WEST);
		// end margins
		
		outer.add(ret,BorderLayout.CENTER);
		
		
		return outer;
	}
	public ImageIcon getSuitIcon(int i){
		return suits[i];
	}
	public static void main(String[] args) {
	}
}
