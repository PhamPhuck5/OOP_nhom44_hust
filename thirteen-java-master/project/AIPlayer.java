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
import java.util.*;
//for thirteen only
public class AIPlayer extends ComputerPlayer {


	public AIPlayer(Thirteen g, String n) {
		super(g, n);
	}

	public Hand getMove(IPlayerAction g) {
		hand.sort();
		ArrayList<VietnameseThirteenHand> v = g.legalMoves();//*note
		
		Hand h = (Hand) v.get((int)Math.floor(Math.random()*v.size()));//Random 1 cach danh hop le bat ky
		hand.removeCards(h);
		System.out.println("Random Player Move:"+h);
		return h;		
	}

	public static void main(String[] args) {
		ComputerPlayer p = new AIPlayer(null,"Test");
		System.out.println(p.getAllTaunts());
	}
}
