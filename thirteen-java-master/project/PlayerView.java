import java.awt.*;
import javax.swing.*;

import deck.ThirteenDeck;

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
public class PlayerView extends JPanel {
    private static final long serialVersionUID = 1L;//*note

	protected Player player;
	protected HandView handView;
	
	public PlayerView(){
		super();
	}
	public PlayerView(Player p,HandView h,boolean vertical) {
		super();
		player = p;
		if(vertical) this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		else this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		this.add(new Avatar(p,0));
		this.add(Box.createRigidArea(new Dimension(20,20)));
		this.add(h);
		this.setBackground(Color.GREEN);
		handView = h;
	}
	public PlayerView(Player p){
		this(p,new HandView(p.getHand()),false);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);	
	}
	public static void main(String[] args) {

		ThirteenGame game = new ThirteenGame();
		game.addPlayer(new QueryPlayer(game,"Jamie"));
		game.addPlayer(new QueryPlayer(game,"Lisa"));
		game.addPlayer(new QueryPlayer(game,"Andrew"));
		game.addPlayer(new QueryPlayer(game,"Yang"));
		ThirteenDeck d = new ThirteenDeck();
		for(int i=0;i<game.playersCount();i++){
			for(int j=0;j<13;j++){
				game.getPlayer(i).getHand().addCard(d.dealCard());
			}
		}
		
		JFrame foo = new JFrame("Avatar Test");
		foo.getContentPane().add(new PlayerView(game.getPlayer(0)));
		foo.pack();
		foo.setVisible(true);	

	}

	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public HandView getHandView() {
		return handView;
	}
	public void setHandView(HandView view) {
		handView = view;
	}

}
