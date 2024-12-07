//import java.util.*;


import hand.Hand;

public class GUIPlayer extends Player {
	Hand selectedHand = null;

	public GUIPlayer(ThirteenGame g, String n) {
		super(g, n);
	}

	public Hand getMove(IPlayerAction g) {
		hand.sort();
//		ArrayList<VietnameseThirteenHand> moves = g.legalMoves();//*note
		waitForSelectedHand();
		Hand move = getSelectedHand();
		hand.removeCards(move);
		setSelectedHand(null);
		return move;
	}

	public synchronized void waitForSelectedHand(){
		while(!selectedHandIsSet()) 
			try{
				wait(200);
			}
			catch(InterruptedException e){	}
	}

	public Hand getSelectedHand() {
		return selectedHand;
	}
	public void setSelectedHand(Hand hand) {
		selectedHand = hand;
	}
	public boolean selectedHandIsSet(){
		return selectedHand != null;
	}
}
