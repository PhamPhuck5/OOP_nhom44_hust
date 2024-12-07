
import java.util.*;


import hand.Hand;
import hand.VietnameseThirteenHand;
//for thirteen only
public class AIPlayer extends ComputerPlayer {
	public AIPlayer(ThirteenGame g, String n) {
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
}
