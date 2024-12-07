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
import javax.swing.*;


import java.awt.*;
import java.io.*;

//import java.awt.image.*;
public class Avatar extends JPanel{
    private static final long serialVersionUID = 1L;//*note

	protected int id;
	protected Player player;
	protected Image portrait;
	/**
	 * 
	 */
	public Avatar(Player p, int id) {
		super();
		player = p;
		File f = new File(GlobalVariabal.projectAddrest+"avatars/"+p.getName()+".gif");
		
		if(f.exists())
			portrait = Toolkit.getDefaultToolkit().getImage(GlobalVariabal.projectAddrest+"avatars/"+p.getName()+".gif");
		else portrait = Toolkit.getDefaultToolkit().getImage(GlobalVariabal.projectAddrest+"avatars/default.gif");
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(new JLabel(p.getName()));
		this.add(new JLabel(new ImageIcon(portrait)));
		this.setBackground(Color.GREEN);
		//this.setPreferredSize(new Dimension(100,100));
	}/*
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(!g.drawImage(portrait,0,0,Color.GREEN,null))
			System.out.println("Image not loaded.");
	}*/
	/*
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
		foo.getContentPane().add(new Avatar(game.getPlayer(0),0));
		foo.pack();
		foo.setVisible(true);	
	}*/
}
